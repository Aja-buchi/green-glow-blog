package com.springbootblog.service.impl;

import com.springbootblog.dto.CommentDto;
import com.springbootblog.entity.Comment;
import com.springbootblog.entity.Post;
import com.springbootblog.exception.ResourceNotFoundException;
import com.springbootblog.repository.CommentRepository;
import com.springbootblog.repository.PostRepository;
import com.springbootblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        //covert Dto to Entity
        Comment comment = mapToEntity(commentDto);

        //retrieve Post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        //set post to comment entity
        comment.setPost(post);

        //save comment entity to BD
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        //retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        //convert list of comment entities to list of comment dto's

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    //covert Entity to Dto
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

    //covert Dto to Entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(comment.getId());
        comment.setName(comment.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setEmail(comment.getEmail());
        return comment;
    }
}
