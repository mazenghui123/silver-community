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
import com.silvercommunity.system.domain.Floor;
import com.silvercommunity.system.service.IFloorService;
import com.silvercommunity.common.utils.poi.ExcelUtil;
import com.silvercommunity.common.core.page.TableDataInfo;

/**
 * 楼层Controller
 * 
 * @author mzh
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/com/floor")
public class FloorController extends BaseController
{
    @Autowired
    private IFloorService floorService;

    /**
     * 查询楼层列表
     */
    @PreAuthorize("@ss.hasPermi('com:floor:list')")
    @GetMapping("/list")
    public TableDataInfo list(Floor floor)
    {
        startPage();
        List<Floor> list = floorService.selectFloorList(floor);
        return getDataTable(list);
    }

    /**
     * 导出楼层列表
     */
    @PreAuthorize("@ss.hasPermi('com:floor:export')")
    @Log(title = "楼层", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Floor floor)
    {
        List<Floor> list = floorService.selectFloorList(floor);
        ExcelUtil<Floor> util = new ExcelUtil<Floor>(Floor.class);
        util.exportExcel(response, list, "楼层数据");
    }

    /**
     * 获取楼层详细信息
     */
    @PreAuthorize("@ss.hasPermi('com:floor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(floorService.selectFloorById(id));
    }

    /**
     * 新增楼层
     */
    @PreAuthorize("@ss.hasPermi('com:floor:add')")
    @Log(title = "楼层", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Floor floor)
    {
        return toAjax(floorService.insertFloor(floor));
    }

    /**
     * 修改楼层
     */
    @PreAuthorize("@ss.hasPermi('com:floor:edit')")
    @Log(title = "楼层", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Floor floor)
    {
        return toAjax(floorService.updateFloor(floor));
    }

    /**
     * 删除楼层
     */
    @PreAuthorize("@ss.hasPermi('com:floor:remove')")
    @Log(title = "楼层", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(floorService.deleteFloorById(id));
    }
}
