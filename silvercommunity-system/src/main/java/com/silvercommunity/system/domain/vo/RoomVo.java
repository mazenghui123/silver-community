package com.silvercommunity.system.domain.vo;


import com.silvercommunity.system.domain.Bed;
import com.silvercommunity.system.domain.Room;
import lombok.Data;

import java.util.List;

@Data
public class RoomVo extends Room {

    private List<BedVo> bedVoList;
}
