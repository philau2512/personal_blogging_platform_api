package com.personal_blog.controller;

import com.personal_blog.config.ApiResponseMessage;
import com.personal_blog.dto.BlogDto;
import com.personal_blog.model.Blog;
import com.personal_blog.model.Category;
import com.personal_blog.service.IBlogService;
import com.personal_blog.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/blogs")
public class RestBlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    // --- Get blogs list ---
    @GetMapping("")
    public ResponseEntity<Page<Blog>> getAllBlogs(@PageableDefault(page = 0, size = 3) Pageable pageable) {

        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageableSorted = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Blog> blogPage = blogService.findAll(pageableSorted);

        if (blogPage.isEmpty()) {
            return new ResponseEntity<>(blogPage, HttpStatus.NO_CONTENT); // 204
        }
        return new ResponseEntity<>(blogPage, HttpStatus.OK); // 200
    }

    // --- View blog by Id ---
    @GetMapping("/view/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return new ResponseEntity<>(blog, HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(blog, HttpStatus.OK); // 200
    }

    // --- Delete blog by id ---
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Blog> deleteBlog(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        if (blog == null) {
            return new ResponseEntity<>(blog, HttpStatus.NOT_FOUND); // 404
        }
        blogService.deleteBlog(id);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

    // --- Create blog ---
    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@RequestBody BlogDto blogDto) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogDto, blog);

        Category category = categoryService.getCategoryById(blogDto.getCategoryId());
        if (category == null) {
            return new ResponseEntity<>(new ApiResponseMessage("Invalid category ID"), HttpStatus.BAD_REQUEST);
        }

        blog.setCategory(category);
        blogService.save(blog);

        return new ResponseEntity<>(blog, HttpStatus.CREATED);
    }


    // --- Update blog ---
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody BlogDto blogDto) {
        Blog existingBlog = blogService.getBlogById(id);
        if (existingBlog == null) {
            return new ResponseEntity<>(new ApiResponseMessage("ID blog không tồn tại"), HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(blogDto, existingBlog, "category");
        Category category = categoryService.getCategoryById(blogDto.getCategoryId());
        if (category == null) {
            return new ResponseEntity<>(new ApiResponseMessage("Invalid category ID"), HttpStatus.BAD_REQUEST);
        }
        existingBlog.setCategory(category);
        blogService.save(existingBlog);
        return new ResponseEntity<>(existingBlog, HttpStatus.OK); // 200
    }
}
