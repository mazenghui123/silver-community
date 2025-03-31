package com.silvercommunity.system.service;

import java.util.List;
import com.silvercommunity.system.domain.Floor;

/**
 * 楼层Service接口
 * 
 * @author mzh
 * @date 2025-03-19
 */
public interface IFloorService 
{
    /**
     * 查询楼层
     * 
     * @param id 楼层主键
     * @return 楼层
     */
    public Floor selectFloorById(Long id);

    /**
     * 查询楼层列表
     * 
     * @param floor 楼层
     * @return 楼层集合
     */
    public List<Floor> selectFloorList(Floor floor);

    /**
     * 新增楼层
     * 
     * @param floor 楼层
     * @return 结果
     */
    public int insertFloor(Floor floor);

    /**
     * 修改楼层
     * 
     * @param floor 楼层
     * @return 结果
     */
    public int updateFloor(Floor floor);

    /**
     * 批量删除楼层
     * 
     * @param ids 需要删除的楼层主键集合
     * @return 结果
     */
    public int deleteFloorByIds(Long[] ids);

    /**
     * 删除楼层信息
     * 
     * @param id 楼层主键
     * @return 结果
     */
    public int deleteFloorById(Long id);
}
