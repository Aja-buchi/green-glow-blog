package com.springbootblog.service.impl;

import com.springbootblog.dto.CommentDto;
import com.springbootblog.entity.Comment;
import com.springbootblog.entity.Post;
import com.springbootblog.exception.BlogAPIException;
import com.springbootblog.exception.ResourceNotFoundException;
import com.springbootblog.repository.CommentRepository;
import com.springbootblog.repository.PostRepository;
import com.springbootblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    private ModelMapper mapper;

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

    @Override
    public CommentDto getCommentsById(Long postId, Long commentId) {
        //retrieve post by postId
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("posts", "id", postId));

        //retrieve comment by commentId
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comments","id", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to this post");
        }
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("posts", "postId", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comments", "commentId", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        //retrieve post by postId
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("posts", "postId", postId));

        //retrieve comment by commentId
        Comment  comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comments", "commentId", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw  new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to Post");
        }
        commentRepository.delete(comment);
    }

    //using mapper to covert Entity to Dto
    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

    //using mapper to covert Dto to Entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        comment.setId(comment.getId());
//        comment.setName(comment.getEmail());
//        comment.setBody(commentDto.getBody());
//        comment.setEmail(comment.getEmail());
        return comment;
    }
}
