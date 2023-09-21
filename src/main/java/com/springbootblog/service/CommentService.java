package com.springbootblog.service;

import com.springbootblog.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    CommentDto createComment (CommentDto commentDto, Long id);

    List<CommentDto> getAllComments(long postId);
}
