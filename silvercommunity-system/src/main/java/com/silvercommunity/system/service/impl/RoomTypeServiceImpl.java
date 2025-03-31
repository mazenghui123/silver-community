package com.silvercommunity.system.service.impl;

import java.util.List;
import com.silvercommunity.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silvercommunity.system.mapper.RoomTypeMapper;
import com.silvercommunity.system.domain.RoomType;
import com.silvercommunity.system.service.IRoomTypeService;

/**
 * 房型Service业务层处理
 * 
 * @author mazh
 * @date 2025-03-13
 */
@Service
public class RoomTypeServiceImpl implements IRoomTypeService 
{
    @Autowired
    private RoomTypeMapper roomTypeMapper;

    /**
     * 查询房型
     * 
     * @param id 房型主键
     * @return 房型
     */
    @Override
    public RoomType selectRoomTypeById(Long id)
    {
        return roomTypeMapper.selectRoomTypeById(id);
    }

    /**
     * 查询房型列表
     * 
     * @param roomType 房型
     * @return 房型
     */
    @Override
    public List<RoomType> selectRoomTypeList(RoomType roomType)
    {
        return roomTypeMapper.selectRoomTypeList(roomType);
    }

    /**
     * 新增房型
     * 
     * @param roomType 房型
     * @return 结果
     */
    @Override
    public int insertRoomType(RoomType roomType)
    {
        roomType.setCreateTime(DateUtils.getNowDate());
        return roomTypeMapper.insertRoomType(roomType);
    }

    /**
     * 修改房型
     * 
     * @param roomType 房型
     * @return 结果
     */
    @Override
    public int updateRoomType(RoomType roomType)
    {
        roomType.setUpdateTime(DateUtils.getNowDate());
        return roomTypeMapper.updateRoomType(roomType);
    }

    /**
     * 批量删除房型
     * 
     * @param ids 需要删除的房型主键
     * @return 结果
     */
    @Override
    public int deleteRoomTypeByIds(Long[] ids)
    {
        return roomTypeMapper.deleteRoomTypeByIds(ids);
    }

    /**
     * 删除房型信息
     * 
     * @param id 房型主键
     * @return 结果
     */
    @Override
    public int deleteRoomTypeById(Long id)
    {
        return roomTypeMapper.deleteRoomTypeById(id);
    }
}
