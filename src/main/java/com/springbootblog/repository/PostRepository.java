package com.springbootblog.repository;

import com.springbootblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //retrieve a list of posts that belong to a particular category
    List<Post> findByCategoryId(Long categoryId);
}
