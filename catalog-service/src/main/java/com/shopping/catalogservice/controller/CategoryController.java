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

    @PostMapping("/save")
    public ResponseEntity<CategoryResponseModel> createCategory(@RequestBody Category category) {
        try{
            logger.info("Start calling category service"+category);
            return new ResponseEntity<>(categoryService.create(category), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryResponseModel> getCategoryById(@PathVariable("id") String categoryId) {
        try{
            return new ResponseEntity<>(categoryService.getById(categoryId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseModel> updateCategory(@PathVariable("id") String categoryId, @RequestBody Category category) {
        try{
            return new ResponseEntity<>(categoryService.update(categoryId,category), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponseModel> deleteCategory(@PathVariable("id") String categoryId) {
        try{
            return new ResponseEntity<>(categoryService.delete(categoryId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteCategories() {
        try{
            return new ResponseEntity<>(categoryService.deleteAll(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
