package com.feelcode.tourism.base.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtils {

    public static String getUuid(){
        String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象
        uuid = uuid.replace("-", "");
        return uuid;
    }

}
