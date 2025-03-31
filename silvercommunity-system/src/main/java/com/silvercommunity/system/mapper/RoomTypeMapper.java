package com.silvercommunity.system.mapper;

import java.util.List;
import com.silvercommunity.system.domain.RoomType;

/**
 * 房型Mapper接口
 * 
 * @author mazh
 * @date 2025-03-13
 */
public interface RoomTypeMapper 
{
    /**
     * 查询房型
     * 
     * @param id 房型主键
     * @return 房型
     */
    public RoomType selectRoomTypeById(Long id);

    /**
     * 查询房型列表
     * 
     * @param roomType 房型
     * @return 房型集合
     */
    public List<RoomType> selectRoomTypeList(RoomType roomType);

    /**
     * 新增房型
     * 
     * @param roomType 房型
     * @return 结果
     */
    public int insertRoomType(RoomType roomType);

    /**
     * 修改房型
     * 
     * @param roomType 房型
     * @return 结果
     */
    public int updateRoomType(RoomType roomType);

    /**
     * 删除房型
     * 
     * @param id 房型主键
     * @return 结果
     */
    public int deleteRoomTypeById(Long id);

    /**
     * 批量删除房型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRoomTypeByIds(Long[] ids);
}
