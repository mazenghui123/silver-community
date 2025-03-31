package com.silvercommunity.system.service.impl;

import java.util.List;
import com.silvercommunity.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silvercommunity.system.mapper.RoomMapper;
import com.silvercommunity.system.domain.Room;
import com.silvercommunity.system.service.IRoomService;

/**
 * 房间Service业务层处理
 * 
 * @author mzh
 * @date 2025-03-19
 */
@Service
public class RoomServiceImpl implements IRoomService 
{
    @Autowired
    private RoomMapper roomMapper;

    /**
     * 查询房间
     * 
     * @param id 房间主键
     * @return 房间
     */
    @Override
    public Room selectRoomById(Long id)
    {
        return roomMapper.selectRoomById(id);
    }

    /**
     * 查询房间列表
     * 
     * @param room 房间
     * @return 房间
     */
    @Override
    public List<Room> selectRoomList(Room room)
    {
        return roomMapper.selectRoomList(room);
    }

    /**
     * 新增房间
     * 
     * @param room 房间
     * @return 结果
     */
    @Override
    public int insertRoom(Room room)
    {
        room.setCreateTime(DateUtils.getNowDate());
        return roomMapper.insertRoom(room);
    }

    /**
     * 修改房间
     * 
     * @param room 房间
     * @return 结果
     */
    @Override
    public int updateRoom(Room room)
    {
        room.setUpdateTime(DateUtils.getNowDate());
        return roomMapper.updateRoom(room);
    }

    /**
     * 批量删除房间
     * 
     * @param ids 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomByIds(Long[] ids)
    {
        return roomMapper.deleteRoomByIds(ids);
    }

    /**
     * 删除房间信息
     * 
     * @param id 房间主键
     * @return 结果
     */
    @Override
    public int deleteRoomById(Long id)
    {
        return roomMapper.deleteRoomById(id);
    }
}
