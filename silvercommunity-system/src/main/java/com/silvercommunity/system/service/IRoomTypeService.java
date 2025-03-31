package com.silvercommunity.system.service;

import java.util.List;
import com.silvercommunity.system.domain.RoomType;

/**
 * 房型Service接口
 * 
 * @author mazh
 * @date 2025-03-13
 */
public interface IRoomTypeService 
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
     * 批量删除房型
     * 
     * @param ids 需要删除的房型主键集合
     * @return 结果
     */
    public int deleteRoomTypeByIds(Long[] ids);

    /**
     * 删除房型信息
     * 
     * @param id 房型主键
     * @return 结果
     */
    public int deleteRoomTypeById(Long id);
}
