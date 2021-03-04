package com.feelcode.tourism.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDateCountVO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dealDate;
    private Long dealNum;
}
