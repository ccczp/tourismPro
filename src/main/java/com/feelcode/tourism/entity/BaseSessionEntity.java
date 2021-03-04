package com.feelcode.tourism.entity;

import lombok.Data;

@Data
public class BaseSessionEntity {

    private String userId;
    private String userName;
    private UserSessionEntity userInfo;

}
