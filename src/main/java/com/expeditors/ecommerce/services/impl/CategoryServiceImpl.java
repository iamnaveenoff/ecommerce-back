package com.expeditors.ecommerce.services.impl;

import com.expeditors.ecommerce.dto.CategoryDTO;
import com.expeditors.ecommerce.entities.Category;
import com.expeditors.ecommerce.repository.CategoryRepository;
import com.expeditors.ecommerce.services.CategoryService;
import com.expeditors.ecommerce.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    public Category publishCategory(String categoryName, MultipartFile image) throws IOException {
        // Store the image in the file system and get the path
        String imagePath = imageStorageService.storeCategoryImage(image);

        // Create and save the category
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryImage(image.getBytes());
        category.setStatus(true);

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long categoryId, String categoryName, MultipartFile image) throws IOException {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (categoryName != null && !categoryName.isEmpty()) {
            category.setCategoryName(categoryName);
        }

        if (image != null && !image.isEmpty()) {
//            String imagePath = imageStorageService.storeCategoryImage(image);
            category.setCategoryImage(image.getBytes());
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        categoryRepository.delete(category);
    }

    @Override
    public List<CategoryDTO> listAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> new CategoryDTO(
                category.getId(),
                category.getCategoryName(),
                category.getStatus()
        )).collect(Collectors.toList());
    }
}
