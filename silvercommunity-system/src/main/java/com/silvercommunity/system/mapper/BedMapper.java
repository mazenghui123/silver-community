package com.silvercommunity.system.mapper;

import java.util.List;
import com.silvercommunity.system.domain.Bed;

/**
 * 床位Mapper接口
 * 
 * @author mzh
 * @date 2025-03-19
 */
public interface BedMapper 
{
    /**
     * 查询床位
     * 
     * @param id 床位主键
     * @return 床位
     */
    public Bed selectBedById(Long id);

    /**
     * 查询床位列表
     * 
     * @param bed 床位
     * @return 床位集合
     */
    public List<Bed> selectBedList(Bed bed);

    /**
     * 新增床位
     * 
     * @param bed 床位
     * @return 结果
     */
    public int insertBed(Bed bed);

    /**
     * 修改床位
     * 
     * @param bed 床位
     * @return 结果
     */
    public int updateBed(Bed bed);

    /**
     * 删除床位
     * 
     * @param id 床位主键
     * @return 结果
     */
    public int deleteBedById(Long id);

    /**
     * 批量删除床位
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBedByIds(Long[] ids);
}
