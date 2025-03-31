package com.silvercommunity.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.silvercommunity.common.annotation.Excel;
import com.silvercommunity.common.core.domain.BaseEntity;

/**
 * 护理任务对象 nursing_task
 * 
 * @author mzh
 * @date 2025-03-20
 */
public class NursingTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 护理员id */
    @Excel(name = "护理员id")
    private Long nursingId;

    /** 项目id */
    @Excel(name = "项目id")
    private Long projectId;

    /** 老人id */
    @Excel(name = "老人id")
    private Long elderId;

    /** 床位编号 */
    @Excel(name = "床位编号")
    private String bedNumber;

    /** 任务类型（2：月度任务，1订单任务） */
    @Excel(name = "任务类型", readConverterExp = "2=：月度任务，1订单任务")
    private Long taskType;

    /** 预计服务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计服务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedServerTime;

    /** 实际服务时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际服务时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date realServerTime;

    /** 执行记录 */
    private String mark;

    /** 取消原因 */
    private String cancelReason;

    /** 状态  1待执行 2已执行 3已关闭  */
    @Excel(name = "状态  1待执行 2已执行 3已关闭 ")
    private Long status;

    /** 订单号 */
    @Excel(name = "订单号")
    private String relNo;

    /** 执行图片 */
    @Excel(name = "执行图片")
    private String taskImage;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNursingId(Long nursingId) 
    {
        this.nursingId = nursingId;
    }

    public Long getNursingId() 
    {
        return nursingId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setElderId(Long elderId) 
    {
        this.elderId = elderId;
    }

    public Long getElderId() 
    {
        return elderId;
    }
    public void setBedNumber(String bedNumber) 
    {
        this.bedNumber = bedNumber;
    }

    public String getBedNumber() 
    {
        return bedNumber;
    }
    public void setTaskType(Long taskType) 
    {
        this.taskType = taskType;
    }

    public Long getTaskType() 
    {
        return taskType;
    }
    public void setEstimatedServerTime(Date estimatedServerTime) 
    {
        this.estimatedServerTime = estimatedServerTime;
    }

    public Date getEstimatedServerTime() 
    {
        return estimatedServerTime;
    }
    public void setRealServerTime(Date realServerTime) 
    {
        this.realServerTime = realServerTime;
    }

    public Date getRealServerTime() 
    {
        return realServerTime;
    }
    public void setMark(String mark) 
    {
        this.mark = mark;
    }

    public String getMark() 
    {
        return mark;
    }
    public void setCancelReason(String cancelReason) 
    {
        this.cancelReason = cancelReason;
    }

    public String getCancelReason() 
    {
        return cancelReason;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setRelNo(String relNo) 
    {
        this.relNo = relNo;
    }

    public String getRelNo() 
    {
        return relNo;
    }
    public void setTaskImage(String taskImage) 
    {
        this.taskImage = taskImage;
    }

    public String getTaskImage() 
    {
        return taskImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nursingId", getNursingId())
            .append("projectId", getProjectId())
            .append("elderId", getElderId())
            .append("bedNumber", getBedNumber())
            .append("taskType", getTaskType())
            .append("estimatedServerTime", getEstimatedServerTime())
            .append("realServerTime", getRealServerTime())
            .append("mark", getMark())
            .append("cancelReason", getCancelReason())
            .append("status", getStatus())
            .append("relNo", getRelNo())
            .append("taskImage", getTaskImage())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
