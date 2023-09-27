package com.springbootblog.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;

    //Field validations
    @NotEmpty
    @Size(min = 4, message = "Name should not be less than 4 characters")
    private String name;

    @NotEmpty(message = "Please enter a valid email")
    @Email
    private String email;

    @Size(min = 10, message = "Post description should have atleast 10 characters")
    @NotEmpty
    private String body;
}
