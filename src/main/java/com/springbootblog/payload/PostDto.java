package com.springbootblog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
public class PostDto {
    private Long id;

    //applying validations to fields
    @NotEmpty
    @Size(min = 2, message = "Post title should have atleast 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have atleast 10 characters")
    private String description;

    @NotEmpty
    private String content;

    //including sets of comments when fetching posts
    private Set<CommentDto> comments;

    //all you attach a post to a category
    private Long categoryId;
}
