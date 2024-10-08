package com.expeditors.ecommerce.dto;

import com.expeditors.ecommerce.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long CategoryId;
    private String categoryName;
    private Boolean status;
//    private String imagePath;


    public CategoryDTO(Category category) {
        this.CategoryId = category.getId();
        this.categoryName = category.getCategoryName();
        this.status = category.getStatus();
    }
}
