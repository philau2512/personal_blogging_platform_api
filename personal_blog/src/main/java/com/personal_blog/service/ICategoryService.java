package com.personal_blog.service;

import com.personal_blog.dto.CategoryDto;
import com.personal_blog.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    void deleteCategory(Long id);
    void save(CategoryDto categoryDto);
}
