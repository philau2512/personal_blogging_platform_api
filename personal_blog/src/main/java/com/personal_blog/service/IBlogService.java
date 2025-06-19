package com.personal_blog.service;

import com.personal_blog.dto.BlogDto;
import com.personal_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    List<Blog> getAllBlogs();
    Blog getBlogById(Long id);
    void save(Blog blog);
    void deleteBlog(Long id);
    Page<Blog> findAll(Pageable pageable);
}
