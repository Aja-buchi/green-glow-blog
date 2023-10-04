package com.springbootblog.controller;

import com.springbootblog.payload.CategoryDto;
import com.springbootblog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable("id") Long categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    //enables authorization header for this endpoint in swagger UI
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
