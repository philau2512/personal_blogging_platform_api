package com.personal_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @GetMapping("")
    public String list() {
        return "redirect:/blog/list.html";
    }

    @GetMapping("/create")
    public String createBlog() {
        return "redirect:/blog/create";
    }

    @GetMapping("/edit")
    public String editBlog() {
        return "redirect:/blog/edit";
    }

    @GetMapping("/view")
    public String viewBlog() {
        return "redirect:/blog/view";
    }
}
