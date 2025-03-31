package com.silvercommunity.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.silvercommunity.common.annotation.Excel;
import com.silvercommunity.common.core.domain.BaseEntity;

/**
 * 楼层对象 base_floor
 * 
 * @author mzh
 * @date 2025-03-19
 */
public class Floor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 编号 */
    @Excel(name = "编号")
    private Long code;

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
    public void setCode(Long code) 
    {
        this.code = code;
    }

    public Long getCode() 
    {
        return code;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
