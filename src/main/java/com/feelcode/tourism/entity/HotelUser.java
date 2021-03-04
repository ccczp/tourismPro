package com.feelcode.tourism.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
public class HotelUser{

    private String hotelId;

    private Date createDate = new Date();

    private Date updateDate;

    private String hotelName;

    private String hotelAddress;

    private String hotelPhone;

    private String hotelPrice;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkInTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkOutTime;

    private String bedType;

    private String hotelDescription;

    private String hotelImages;

    private String userId;

    private String userName;

}
