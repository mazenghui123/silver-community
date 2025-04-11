package com.silvercommunity.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.silvercommunity.system.domain.vo.BedVo;
import com.silvercommunity.system.domain.vo.RoomVo;
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
import com.silvercommunity.system.domain.Room;
import com.silvercommunity.system.service.IRoomService;
import com.silvercommunity.common.utils.poi.ExcelUtil;
import com.silvercommunity.common.core.page.TableDataInfo;

/**
 * 房间Controller
 * 
 * @author mzh
 * @date 2025-03-19
 */
@RestController
@RequestMapping("/com/room")
public class RoomController extends BaseController
{
    @Autowired
    private IRoomService roomService;

    /**
     * 查询房间列表
     */
    @PreAuthorize("@ss.hasPermi('com:room:list')")
    @GetMapping("/list")
    public TableDataInfo list(Room room)
    {
        startPage();
        List<Room> list = roomService.selectRoomList(room);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('com:room:list')")
    @GetMapping("/RoomVoList")
    public TableDataInfo getRoomList(Long floorId) {
        startPage();
        List<RoomVo> list = roomService.getRoomsByFloorId(floorId);
        return getDataTable(list);
    }

    /**
     * 导出房间列表
     */
    @PreAuthorize("@ss.hasPermi('com:room:export')")
    @Log(title = "房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Room room)
    {
        List<Room> list = roomService.selectRoomList(room);
        ExcelUtil<Room> util = new ExcelUtil<Room>(Room.class);
        util.exportExcel(response, list, "房间数据");
    }

    /**
     * 获取房间详细信息
     */
    @PreAuthorize("@ss.hasPermi('com:room:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(roomService.selectRoomById(id));
    }

    /**
     * 新增房间
     */
    @PreAuthorize("@ss.hasPermi('com:room:add')")
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Room room)
    {
        return toAjax(roomService.insertRoom(room));
    }

    /**
     * 修改房间
     */
    @PreAuthorize("@ss.hasPermi('com:room:edit')")
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Room room)
    {
        return toAjax(roomService.updateRoom(room));
    }

    /**
     * 删除房间
     */
    @PreAuthorize("@ss.hasPermi('com:room:remove')")
    @Log(title = "房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(roomService.deleteRoomByIds(ids));
    }
}
