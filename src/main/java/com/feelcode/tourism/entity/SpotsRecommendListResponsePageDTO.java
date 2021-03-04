package com.feelcode.tourism.entity;

import lombok.Data;

import java.util.List;

@Data
public class SpotsRecommendListResponsePageDTO extends CommonResponsePageDTO{

    private List<Spots> spotsList;
    private Spots spots;

}
