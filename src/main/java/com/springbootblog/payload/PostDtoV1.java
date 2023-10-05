package com.springbootblog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Customizes swagger API documentation
@Schema(
        description = "PostDto Model Information"
)
public class PostDtoV1 {
    private Long id;

    //applying validations to fields
    @NotEmpty
    @Size(min = 2, message = "Post title should have atleast 2 characters")
    //Customizes swagger API documentation
    @Schema(
            description = "Blog Post Title"
    )
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have atleast 10 characters")
    //Customizes swagger API documentation
    @Schema(
            description = "Blog Post Description"
    )
    private String description;

    @NotEmpty
    //Customizes swagger API documentation
    @Schema(
            description = "Blog Post Content"
    )
    private String content;

    //including sets of comments when fetching posts
    private Set<CommentDto> comments;

    //used for V1 versioning
    private List<String> tags;

    //all you attach a post to a category
    private Long categoryId;
}
