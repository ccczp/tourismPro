package com.feelcode.tourism.entity;

import lombok.Data;

import java.util.List;

@Data
public class CommentResponsePageDTO extends CommonResponsePageDTO{
    private List<Comment> commentList;
}
