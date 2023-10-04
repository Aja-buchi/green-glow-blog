package com.springbootblog.controller;

import com.springbootblog.payload.CategoryDto;
import com.springbootblog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
//Customizes swagger API documentation
@Tag(
        name = "CRUD REST APIs for Category Resource"
)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    //Customizes swagger API documentation
    @Operation(
            summary = "Create Category REST API",
            description = "Create Category REST API is used to save a category to a post to the database "
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    //Customizes swagger API documentation
    @Operation(
            summary = "Get Category By Id REST API",
            description = "Get Category By Id REST API is used to get a category by Id from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity <List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    //Customizes swagger API documentation
    @Operation(
            summary = "Update Comment By Id REST API",
            description = "Update Comment By Id REST API is used to update a particular comment in the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    //Customizes swagger API documentation
    @Operation(
            summary = "Delete Category By Id REST API",
            description = "Delete Category By Id REST API is used to delete a particular category from the database "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
