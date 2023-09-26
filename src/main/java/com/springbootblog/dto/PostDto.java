package com.springbootblog.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    //including sets of comments when fetching posts
    private Set<CommentDto> commentDtoSet;
}
