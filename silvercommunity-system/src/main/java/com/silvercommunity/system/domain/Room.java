package com.silvercommunity.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.silvercommunity.common.annotation.Excel;
import com.silvercommunity.common.core.domain.BaseEntity;

/**
 * 房间对象 base_room
 * 
 * @author mzh
 * @date 2025-03-19
 */
public class Room extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 房间编号 */
    @Excel(name = "房间编号")
    private String code;

    /** 排序号 */
    private Long sort;

    /** 房间类型id */
    private Long typeId;

    /** 房型名 */
    @Excel(name = "房型名")
    private String typeName;

    /** 楼层 */
    @Excel(name = "楼层")
    private Long floorId;

    /** 是否删除 */
    private Integer isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setFloorId(Long floorId) 
    {
        this.floorId = floorId;
    }

    public Long getFloorId() 
    {
        return floorId;
    }
    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("sort", getSort())
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("floorId", getFloorId())
            .append("remark", getRemark())
            .append("isDeleted", getIsDeleted())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
