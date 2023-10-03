package com.springbootblog.service;

import com.springbootblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment (CommentDto commentDto, Long id);

    List<CommentDto> getAllComments(long postId);

    CommentDto getCommentsById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);

    void deleteComment(Long postId,Long commentId);
}
