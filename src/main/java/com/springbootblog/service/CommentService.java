package com.springbootblog.service;

import com.springbootblog.dto.CommentDto;
import org.springframework.stereotype.Service;

public interface CommentService {
    CommentDto createComment (CommentDto commentDto, Long id);
}
