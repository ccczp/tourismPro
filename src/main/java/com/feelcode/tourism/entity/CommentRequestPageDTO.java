package com.feelcode.tourism.entity;

import lombok.Data;

@Data
public class CommentRequestPageDTO extends CommonRequestPageDTO{
    private String productId;
    private Integer productType;
}
