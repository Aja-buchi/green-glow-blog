package com.springbootblog.controller;

import com.springbootblog.payload.CommentDto;
import com.springbootblog.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity <List<CommentDto>> getCommentsByPostId(@PathVariable Long postId){
        return  new ResponseEntity<>(commentService.getAllComments(postId),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentsById(@PathVariable Long postId, @PathVariable Long commentId){
        return  new ResponseEntity<>(commentService.getCommentsById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<CommentDto> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
