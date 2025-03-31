package com.silvercommunity.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.silvercommunity.common.annotation.Log;
import com.silvercommunity.common.core.controller.BaseController;
import com.silvercommunity.common.core.domain.AjaxResult;
import com.silvercommunity.common.enums.BusinessType;
import com.silvercommunity.system.domain.Bed;
import com.silvercommunity.system.service.IBedService;
import com.silvercommunity.common.utils.poi.ExcelUtil;
import com.silvercommunity.common.core.page.TableDataInfo;

/**
 * 床位Controller
 * 
 * @author mzh
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/com/bed")
public class BedController extends BaseController
{
    @Autowired
    private IBedService bedService;

    /**
     * 查询床位列表
     */
    @PreAuthorize("@ss.hasPermi('com:bed:list')")
    @GetMapping("/list")
    public TableDataInfo list(Bed bed)
    {
        startPage();
        List<Bed> list = bedService.selectBedList(bed);
        return getDataTable(list);
    }

    /**
     * 导出床位列表
     */
    @PreAuthorize("@ss.hasPermi('com:bed:export')")
    @Log(title = "床位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Bed bed)
    {
        List<Bed> list = bedService.selectBedList(bed);
        ExcelUtil<Bed> util = new ExcelUtil<Bed>(Bed.class);
        util.exportExcel(response, list, "床位数据");
    }

    /**
     * 获取床位详细信息
     */
    @PreAuthorize("@ss.hasPermi('com:bed:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(bedService.selectBedById(id));
    }

    /**
     * 新增床位
     */
    @PreAuthorize("@ss.hasPermi('com:bed:add')")
    @Log(title = "床位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bed bed)
    {
        return toAjax(bedService.insertBed(bed));
    }

    /**
     * 修改床位
     */
    @PreAuthorize("@ss.hasPermi('com:bed:edit')")
    @Log(title = "床位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bed bed)
    {
        return toAjax(bedService.updateBed(bed));
    }

    /**
     * 删除床位
     */
    @PreAuthorize("@ss.hasPermi('com:bed:remove')")
    @Log(title = "床位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(bedService.deleteBedByIds(ids));
    }
}
