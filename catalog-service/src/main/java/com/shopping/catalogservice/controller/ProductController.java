package com.shopping.catalogservice.controller;

import com.shopping.catalogservice.entity.Category;
import com.shopping.catalogservice.entity.Product;
import com.shopping.catalogservice.model.CategoryResponseModel;
import com.shopping.catalogservice.model.ProductResponseModel;
import com.shopping.catalogservice.service.ICategoryService;
import com.shopping.catalogservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("catalog/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/")
    public String productController(){
        return "Welcome to product controller!";
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponseModel> createProduct(@RequestBody Product product) {
        try{
            return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductResponseModel> getProductById(@PathVariable("id") String productId) {
        try{
            return new ResponseEntity<>(productService.getById(productId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseModel> updateProduct(@PathVariable("id") String productId, @RequestBody Product product) {
        try{
            return new ResponseEntity<>(productService.update(productId,product), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductResponseModel> deleteProduct(@PathVariable("id") String productId) {
        try{
            return new ResponseEntity<>(productService.delete(productId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByCategoryId/{id}")
    public ResponseEntity  getByCategoryId(@PathVariable("id") String categoryId){
        try{
            return new ResponseEntity<>(productService.getProductsByCategoryId(categoryId), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
