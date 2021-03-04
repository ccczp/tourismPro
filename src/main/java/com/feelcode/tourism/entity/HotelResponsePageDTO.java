package com.feelcode.tourism.entity;

import lombok.Data;

import java.util.List;

@Data
public class HotelResponsePageDTO extends CommonResponsePageDTO{
    private List<Hotel> hotelList;
}
