package com.expeditors.ecommerce.controller;

import com.expeditors.ecommerce.dto.CategoryDTO;
import com.expeditors.ecommerce.entities.Category;
import com.expeditors.ecommerce.services.CategoryService;
import com.expeditors.ecommerce.utils.ResponseMessage;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> publishCategory(
            @RequestParam String categoryName,
            @RequestParam MultipartFile image) {
        try {
            Category category = categoryService.publishCategory(categoryName, image);
            ResponseMessage<Category> response = new ResponseMessage<>(HttpStatus.OK.value(), "Category created successfully", category, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create category", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateCategory(
            @PathVariable Long categoryId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) MultipartFile image) {
        try {
            Category category = categoryService.updateCategory(categoryId, categoryName, image);
            ResponseMessage<Category> response = new ResponseMessage<>(HttpStatus.OK.value(), "Category updated successfully", category, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update category", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.OK.value(), "Category deleted successfully", null, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete category", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/listAllCategory")
    public ResponseEntity<?> listAllCategories() {
        try {
            List<CategoryDTO> categories = categoryService.listAllCategories();
            ResponseMessage<List<CategoryDTO>> response = new ResponseMessage<>(HttpStatus.OK.value(), "Categories retrieved successfully", categories, null);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseMessage<String> response = new ResponseMessage<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to retrieve categories", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
