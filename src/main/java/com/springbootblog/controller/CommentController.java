package com.springbootblog.controller;

import com.springbootblog.payload.CommentDto;
import com.springbootblog.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//Customizes swagger API documentation
@Tag(
        name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    //Customizes swagger API documentation
    @Operation(
            summary = "Create Comment REST API",
            description = "Create Comment REST API is used to save a comment to a post to the database "
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    //Customizes swagger API documentation
    @Operation(
            summary = "Get Comments By PostId REST API",
            description = "Get Comments By PostId REST API is used to get a post by postId from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity <List<CommentDto>> getCommentsByPostId(@PathVariable Long postId){
        return  new ResponseEntity<>(commentService.getAllComments(postId),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    //Customizes swagger API documentation
    @Operation(
            summary = "Get Comments By Id REST API",
            description = "Get Comments By Id REST API is used to get a post by Id from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable Long postId, @PathVariable Long commentId){
        return  new ResponseEntity<>(commentService.getCommentsById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    //Customizes swagger API documentation
    @Operation(
            summary = "Update Comment By Id REST API",
            description = "Update Comment By Id REST API is used to update a particular comment in the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public  ResponseEntity<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    //Customizes swagger API documentation
    @Operation(
            summary = "Delete Comment By Id REST API",
            description = "Delete Comment By Id REST API is used to delete a particular comment from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
