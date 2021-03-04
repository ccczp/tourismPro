package com.feelcode.tourism.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HotelRequestPageDTO extends CommonRequestPageDTO{
    private String hotelName;
    private String hotelAddress;
    private String hotelPriceMin;
    private String hotelPriceMax;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String checkInTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String checkOutTime;
    private String bedType;
    private String hotelPerson;
    private String star;
    /**
     * 优惠类型
     */
    private String offerType;

}
