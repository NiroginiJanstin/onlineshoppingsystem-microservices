package com.shopping.catalogservice.controller;

import com.shopping.catalogservice.entity.Category;
import com.shopping.catalogservice.model.CategoryResponseModel;
import com.shopping.catalogservice.service.CategoryService;
import com.shopping.catalogservice.service.ICategoryService;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("catalog/category")
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    ICategoryService categoryService;

    @GetMapping("/")
    public String categoryController(){
        return "Welcome to category controller!";
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<CategoryResponseModel> createCategory(@RequestBody Category category) {
        try{
            logger.info("Start calling category service"+category);
            return new ResponseEntity<>(categoryService.createNew(category), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
