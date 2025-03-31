package com.silvercommunity.system.service;

import java.util.List;
import com.silvercommunity.system.domain.Room;

/**
 * 房间Service接口
 * 
 * @author mzh
 * @date 2025-03-19
 */
public interface IRoomService 
{
    /**
     * 查询房间
     * 
     * @param id 房间主键
     * @return 房间
     */
    public Room selectRoomById(Long id);

    /**
     * 查询房间列表
     * 
     * @param room 房间
     * @return 房间集合
     */
    public List<Room> selectRoomList(Room room);

    /**
     * 新增房间
     * 
     * @param room 房间
     * @return 结果
     */
    public int insertRoom(Room room);

    /**
     * 修改房间
     * 
     * @param room 房间
     * @return 结果
     */
    public int updateRoom(Room room);

    /**
     * 批量删除房间
     * 
     * @param ids 需要删除的房间主键集合
     * @return 结果
     */
    public int deleteRoomByIds(Long[] ids);

    /**
     * 删除房间信息
     * 
     * @param id 房间主键
     * @return 结果
     */
    public int deleteRoomById(Long id);
}
