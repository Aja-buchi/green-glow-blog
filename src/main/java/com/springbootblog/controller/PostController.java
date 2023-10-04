package com.springbootblog.controller;

import com.springbootblog.entity.Post;
import com.springbootblog.payload.PostDto;
import com.springbootblog.payload.PostResponse;
import com.springbootblog.service.PostService;
import com.springbootblog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
//Customizes swagger API documentation
@Tag(
        name = "CRUD REST APIs for Post Resource"
)
public class PostController {

    @Autowired
    private PostService postService;

    //Create post Rest api
    @PostMapping
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')") //only admin can create post
    //Customizes swagger API documentation
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into the database "
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    public ResponseEntity<PostDto> createPost (@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //Get all post Rest api
    @GetMapping
    //Customizes swagger API documentation
    @Operation(
            summary = "Get All Post REST API",
            description = "Get All Post REST API is used to fetch all posts from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    //Get all post by id Rest api with pagination
    public PostResponse getAllPosts(
            //implement pagination & sorting
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDir){

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    //Get post by id Rest api
    @GetMapping("/{id}")
    //Customizes swagger API documentation
    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get a post by id from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
       return ResponseEntity.ok(postService.getPostById(id));
    }

    //Update post by id Rest api
    @PutMapping("/{id}")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')") //only admin can update post
    //Customizes swagger API documentation
    @Operation(
            summary = "Update Post By Id REST API",
            description = "Update Post By Id REST API is used to update a particular post in the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Long id){
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //Update post by id Rest api
    @DeleteMapping("/{id}")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    @PreAuthorize("hasRole('ADMIN')") //only admin can delete post
    //Customizes swagger API documentation
    @Operation(
            summary = "Delete Post By Id REST API",
            description = "Delete Post By Id REST API is used to delete a particular post from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

    //Build Get Post by Category REST API
    @GetMapping("/category/{id}")
    //Customizes swagger API documentation
    @Operation(
            summary = "Get Post By CategoryId REST API",
            description = "Get Post By CategoryId REST API is used to get a particular post from the database using categoryId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity <List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId){
       List<PostDto> postDto = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
}
