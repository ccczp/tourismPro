package com.feelcode.tourism.entity;

import lombok.Data;

import java.util.List;

@Data
public class GroupResponsePageDTO extends CommonResponsePageDTO{
    private List<Group> groupList;
}
