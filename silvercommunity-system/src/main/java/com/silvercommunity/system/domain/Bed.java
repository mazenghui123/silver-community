package com.silvercommunity.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.silvercommunity.common.annotation.Excel;
import com.silvercommunity.common.core.domain.BaseEntity;

/**
 * 床位对象 base_bed
 * 
 * @author mzh
 * @date 2025-03-19
 */
public class Bed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 床位ID */
    private Long id;

    /** 床位编号 */
    @Excel(name = "床位编号")
    private String bedNumber;

    /** 状态 */
    @Excel(name = "状态")
    private Long bedStatus;

    /** 床位号 */
    @Excel(name = "床位号")
    private Long sort;

    /** 楼层ID */
    @Excel(name = "楼层ID")
    private Long floorId;

    /** 房间ID */
    @Excel(name = "房间ID")
    private Long roomId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBedNumber(String bedNumber) 
    {
        this.bedNumber = bedNumber;
    }

    public String getBedNumber() 
    {
        return bedNumber;
    }
    public void setBedStatus(Long bedStatus) 
    {
        this.bedStatus = bedStatus;
    }

    public Long getBedStatus() 
    {
        return bedStatus;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setFloorId(Long floorId) 
    {
        this.floorId = floorId;
    }

    public Long getFloorId() 
    {
        return floorId;
    }
    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bedNumber", getBedNumber())
            .append("bedStatus", getBedStatus())
            .append("sort", getSort())
            .append("floorId", getFloorId())
            .append("roomId", getRoomId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
