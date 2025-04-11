package com.silvercommunity.system.domain.vo;

import com.silvercommunity.system.domain.Bed;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BedVo extends Bed {

    private List<String> nursingNames;

    private List<Long> nursingIds;
    //护理员
    /*private List<UserVo> userVos;*/
    //老人姓名
    private String name;

    private String floorName;

    private String typeName;

    private String code;

    private BigDecimal price;

    private Long elderId;

    private Integer status = 0;
    //等级名称
    /*private String lname;*/


}
