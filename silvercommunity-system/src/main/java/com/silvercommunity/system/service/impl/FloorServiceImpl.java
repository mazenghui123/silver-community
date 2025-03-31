package com.silvercommunity.system.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.silvercommunity.common.utils.DateUtils;
import com.silvercommunity.system.domain.Room;
import com.silvercommunity.system.mapper.BedMapper;
import com.silvercommunity.system.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silvercommunity.system.mapper.FloorMapper;
import com.silvercommunity.system.domain.Floor;
import com.silvercommunity.system.service.IFloorService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 楼层Service业务层处理
 *
 * @author mzh
 * @date 2025-03-19
 */
@Service
public class FloorServiceImpl implements IFloorService {
    @Autowired
    private FloorMapper floorMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private BedMapper bedrockMapper;

    /**
     * 查询楼层
     *
     * @param id 楼层主键
     * @return 楼层
     */
    @Override
    public Floor selectFloorById(Long id) {
        return floorMapper.selectFloorById(id);
    }

    /**
     * 查询楼层列表
     *
     * @param floor 楼层
     * @return 楼层
     */
    @Override
    public List<Floor> selectFloorList(Floor floor) {
        return floorMapper.selectFloorList(floor);
    }

    /**
     * 新增楼层
     *
     * @param floor 楼层
     * @return 结果
     */
    @Override
    public int insertFloor(Floor floor) {
        floor.setCreateTime(DateUtils.getNowDate());
        return floorMapper.insertFloor(floor);
    }

    /**
     * 修改楼层
     *
     * @param floor 楼层
     * @return 结果
     */
    @Override
    @Transactional
    public int updateFloor(Floor floor) {
        floor.setUpdateTime(DateUtils.getNowDate());
        UpdateWrapper<Room> roomWrapper = new UpdateWrapper<>();
        roomWrapper.lambda().eq(Room::getFloorId, floor.getId())
                .set(Room::getFloorId, floor.getId());

        return floorMapper.updateFloor(floor);
    }

    /**
     * 批量删除楼层
     *
     * @param ids 需要删除的楼层主键
     * @return 结果
     */
    @Override
    public int deleteFloorByIds(Long[] ids) {
        return floorMapper.deleteFloorByIds(ids);
    }

    /**
     * 删除楼层信息
     *
     * @param id 楼层主键
     * @return 结果
     */
    @Override
    public int deleteFloorById(Long id) {
        return floorMapper.deleteFloorById(id);
    }
}
