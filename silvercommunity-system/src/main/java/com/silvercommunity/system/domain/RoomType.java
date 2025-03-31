package com.silvercommunity.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.silvercommunity.common.annotation.Excel;
import com.silvercommunity.common.core.domain.BaseEntity;

/**
 * 房型对象 base_room_type
 * 
 * @author mazh
 * @date 2025-03-13
 */
public class RoomType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 房型名称 */
    @Excel(name = "房型名称")
    private String name;

    /** 床位数量 */
    @Excel(name = "床位数量")
    private Long bedCount;

    /** 费用 */
    @Excel(name = "费用")
    private BigDecimal price;

    /** 介绍 */
    @Excel(name = "介绍")
    private String introduction;

    /** 照片 */
    @Excel(name = "照片")
    private String photo;

    /** 状态，0：禁用，1：启用 */
    @Excel(name = "状态，0：禁用，1：启用")
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setBedCount(Long bedCount) 
    {
        this.bedCount = bedCount;
    }

    public Long getBedCount() 
    {
        return bedCount;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("bedCount", getBedCount())
            .append("price", getPrice())
            .append("introduction", getIntroduction())
            .append("photo", getPhoto())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
