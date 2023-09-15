package com.springbootblog.service;

import com.springbootblog.dto.PostDto;
import com.springbootblog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDto createPost (PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);
}
