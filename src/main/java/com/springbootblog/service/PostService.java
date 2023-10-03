package com.springbootblog.service;

import com.springbootblog.payload.PostDto;
import com.springbootblog.payload.PostResponse;

public interface PostService {
    PostDto createPost (PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    void deletePostById (Long id);

    PostDto updatePost(PostDto postDto, Long id);
}
