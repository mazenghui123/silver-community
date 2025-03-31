package com.silvercommunity.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.silvercommunity.system.domain.Room;

/**
 * 房间Mapper接口
 *
 * @author mzh
 * @date 2025-03-19
 */
public interface RoomMapper extends BaseMapper<Room> {
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
     * 删除房间
     *
     * @param id 房间主键
     * @return 结果
     */
    public int deleteRoomById(Long id);

    /**
     * 批量删除房间
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoomByIds(Long[] ids);
}
