package com.expeditors.ecommerce.services;

import com.expeditors.ecommerce.dto.CategoryDTO;
import com.expeditors.ecommerce.entities.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface CategoryService {

    public Category publishCategory(String categoryName, MultipartFile image) throws IOException;
    public Category updateCategory(Long categoryId, String categoryName, MultipartFile image) throws IOException;

    public void deleteCategory(Long categoryId);

    public List<CategoryDTO> listAllCategories();

}
