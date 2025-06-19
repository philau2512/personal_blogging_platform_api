package com.personal_blog.controller;

import com.personal_blog.config.ApiResponseMessage;
import com.personal_blog.dto.CategoryDto;
import com.personal_blog.model.Blog;
import com.personal_blog.model.Category;
import com.personal_blog.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class RestCategoryController {
    @Autowired
    private ICategoryService categoryService;

    // --- get list category ---
    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categoriesList = categoryService.getAllCategories();
        if (categoriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CategoryDto> categoryDTOList = new ArrayList<>();
        for (Category category : categoriesList) {
            CategoryDto dto = new CategoryDto();
            BeanUtils.copyProperties(category, dto);
            categoryDTOList.add(dto);
        }

        return new ResponseEntity<>(categoryDTOList, HttpStatus.OK);
    }

    // --- Delete category by id ---
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>(new ApiResponseMessage("Category ID not found"), HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponseMessage("Delete Success !"), HttpStatus.OK);
    }

    // --- Create category ---
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    // --- Update category ---
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category categoryExisting = categoryService.getCategoryById(id);
        if (categoryExisting == null) {
            return new ResponseEntity<>(new ApiResponseMessage("ID category not found!"), HttpStatus.NOT_FOUND);
        }
        if (categoryDto.getId() == null) {
            categoryDto.setId(id);
        }
        categoryExisting.setName(categoryDto.getName());
        categoryService.save(categoryDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    // --- Get list Blogs by Category Id ---
    @GetMapping("/{id}/blogs")
    public ResponseEntity<?> getBlogsByCategoryId(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return new ResponseEntity<>(new ApiResponseMessage("Category ID not found"), HttpStatus.NOT_FOUND);
        }
        List<Blog> blogs = category.getBlogList();
        if (blogs.isEmpty()) {
            return new ResponseEntity<>(new ApiResponseMessage("Category has no blogs"), HttpStatus.OK);
        }
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }
}
