package com.silvercommunity.system.service.impl;

import java.util.List;
import com.silvercommunity.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silvercommunity.system.mapper.BedMapper;
import com.silvercommunity.system.domain.Bed;
import com.silvercommunity.system.service.IBedService;

/**
 * 床位Service业务层处理
 * 
 * @author mzh
 * @date 2025-03-19
 */
@Service
public class BedServiceImpl implements IBedService 
{
    @Autowired
    private BedMapper bedMapper;

    /**
     * 查询床位
     * 
     * @param id 床位主键
     * @return 床位
     */
    @Override
    public Bed selectBedById(Long id)
    {
        return bedMapper.selectBedById(id);
    }

    /**
     * 查询床位列表
     * 
     * @param bed 床位
     * @return 床位
     */
    @Override
    public List<Bed> selectBedList(Bed bed)
    {
        return bedMapper.selectBedList(bed);
    }

    /**
     * 新增床位
     * 
     * @param bed 床位
     * @return 结果
     */
    @Override
    public int insertBed(Bed bed)
    {
        bed.setCreateTime(DateUtils.getNowDate());
        return bedMapper.insertBed(bed);
    }

    /**
     * 修改床位
     * 
     * @param bed 床位
     * @return 结果
     */
    @Override
    public int updateBed(Bed bed)
    {
        bed.setUpdateTime(DateUtils.getNowDate());
        return bedMapper.updateBed(bed);
    }

    /**
     * 批量删除床位
     * 
     * @param ids 需要删除的床位主键
     * @return 结果
     */
    @Override
    public int deleteBedByIds(Long[] ids)
    {
        return bedMapper.deleteBedByIds(ids);
    }

    /**
     * 删除床位信息
     * 
     * @param id 床位主键
     * @return 结果
     */
    @Override
    public int deleteBedById(Long id)
    {
        return bedMapper.deleteBedById(id);
    }
}
