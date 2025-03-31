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
import com.silvercommunity.system.domain.RoomType;
import com.silvercommunity.system.service.IRoomTypeService;
import com.silvercommunity.common.utils.poi.ExcelUtil;
import com.silvercommunity.common.core.page.TableDataInfo;

/**
 * 房型Controller
 *
 * @author mazh
 * @date 2025-03-13
 */
@RestController
@RequestMapping("/com/RoomType")
public class RoomTypeController extends BaseController {
    @Autowired
    private IRoomTypeService roomTypeService;

    /**
     * 查询房型列表
     */
    @PreAuthorize("@ss.hasPermi('com:RoomType:list')")
    @GetMapping("/list")
    public TableDataInfo list(RoomType roomType) {
        startPage();
        List<RoomType> list = roomTypeService.selectRoomTypeList(roomType);
        return getDataTable(list);
    }

    /**
     * 导出房型列表
     */
    @PreAuthorize("@ss.hasPermi('com:RoomType:export')")
    @Log(title = "房型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RoomType roomType) {
        List<RoomType> list = roomTypeService.selectRoomTypeList(roomType);
        ExcelUtil<RoomType> util = new ExcelUtil<RoomType>(RoomType.class);
        util.exportExcel(response, list, "房型数据");
    }

    /**
     * 获取房型详细信息
     */
    @PreAuthorize("@ss.hasPermi('com:RoomType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(roomTypeService.selectRoomTypeById(id));
    }

    /**
     * 新增房型
     */
    @PreAuthorize("@ss.hasPermi('com:RoomType:add')")
    @Log(title = "房型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RoomType roomType) {
        return toAjax(roomTypeService.insertRoomType(roomType));
    }

    /**
     * 修改房型
     */
    @PreAuthorize("@ss.hasPermi('com:RoomType:edit')")
    @Log(title = "房型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RoomType roomType) {
        return toAjax(roomTypeService.updateRoomType(roomType));
    }

    /**
     * 删除房型
     */
    @PreAuthorize("@ss.hasPermi('com:RoomType:remove')")
    @Log(title = "房型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(roomTypeService.deleteRoomTypeByIds(ids));
    }
}
