package com.feelcode.tourism.entity;

import lombok.Data;

import java.util.List;

@Data
public class LineDetailResponseDTO {
    private Line line;
    private List<Group> groupList;
}
