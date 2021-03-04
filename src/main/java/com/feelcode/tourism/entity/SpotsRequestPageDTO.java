package com.feelcode.tourism.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SpotsRequestPageDTO extends CommonRequestPageDTO{

    @NotBlank(message = "请输入您想查询的景点名称")
    private String spotsName;

}
