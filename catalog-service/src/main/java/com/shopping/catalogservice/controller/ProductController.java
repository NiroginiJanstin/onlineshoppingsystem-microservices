package com.shopping.catalogservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class ProductController {

    @GetMapping("/")
    public String getStudentWithCourse(){
        return "Welcome to product controller!";
    }
}
