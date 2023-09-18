package com.springbootblog.service;

import com.springbootblog.dto.PostDto;
import com.springbootblog.dto.PostResponse;
import com.springbootblog.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    PostDto createPost (PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    void deletePostById (Long id);

    PostDto updatePost(PostDto postDto, Long id);
}
