package com.silvercommunity.system.service.impl;

import java.util.List;
import com.silvercommunity.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silvercommunity.system.mapper.NursingTaskMapper;
import com.silvercommunity.system.domain.NursingTask;
import com.silvercommunity.system.service.INursingTaskService;

/**
 * 护理任务Service业务层处理
 * 
 * @author mzh
 * @date 2025-03-20
 */
@Service
public class NursingTaskServiceImpl implements INursingTaskService 
{
    @Autowired
    private NursingTaskMapper nursingTaskMapper;

    /**
     * 查询护理任务
     * 
     * @param id 护理任务主键
     * @return 护理任务
     */
    @Override
    public NursingTask selectNursingTaskById(Long id)
    {
        return nursingTaskMapper.selectNursingTaskById(id);
    }

    /**
     * 查询护理任务列表
     * 
     * @param nursingTask 护理任务
     * @return 护理任务
     */
    @Override
    public List<NursingTask> selectNursingTaskList(NursingTask nursingTask)
    {
        return nursingTaskMapper.selectNursingTaskList(nursingTask);
    }

    /**
     * 新增护理任务
     * 
     * @param nursingTask 护理任务
     * @return 结果
     */
    @Override
    public int insertNursingTask(NursingTask nursingTask)
    {
        nursingTask.setCreateTime(DateUtils.getNowDate());
        return nursingTaskMapper.insertNursingTask(nursingTask);
    }

    /**
     * 修改护理任务
     * 
     * @param nursingTask 护理任务
     * @return 结果
     */
    @Override
    public int updateNursingTask(NursingTask nursingTask)
    {
        nursingTask.setUpdateTime(DateUtils.getNowDate());
        return nursingTaskMapper.updateNursingTask(nursingTask);
    }

    /**
     * 批量删除护理任务
     * 
     * @param ids 需要删除的护理任务主键
     * @return 结果
     */
    @Override
    public int deleteNursingTaskByIds(Long[] ids)
    {
        return nursingTaskMapper.deleteNursingTaskByIds(ids);
    }

    /**
     * 删除护理任务信息
     * 
     * @param id 护理任务主键
     * @return 结果
     */
    @Override
    public int deleteNursingTaskById(Long id)
    {
        return nursingTaskMapper.deleteNursingTaskById(id);
    }
}
