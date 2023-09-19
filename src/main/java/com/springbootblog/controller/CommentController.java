package com.springbootblog.controller;

import com.springbootblog.dto.CommentDto;
import com.springbootblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }
}
