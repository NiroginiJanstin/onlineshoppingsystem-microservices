package com.shopping.catalogservice.service;

import com.shopping.catalogservice.entity.Product;
import com.shopping.catalogservice.model.ProductResponseModel;
import com.shopping.catalogservice.repository.IProductRepository;
import com.shopping.catalogservice.utils.validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICategoryService categoryService;

    @Override
    public ProductResponseModel create(Product product) {
        ProductResponseModel responseModel =  new ProductResponseModel();
        boolean productNameError = validation.emptyFieldValidation(product.getProductName());
        boolean productImageUrlError = validation.emptyFieldValidation(product.getProductImageUrl());
        boolean productCategoryError = validation.emptyFieldValidation(product.getCategoryId());

        if(productNameError){
            responseModel.product = null;
            responseModel.message = "Please provide product name";
        }

        else if(productImageUrlError){
            responseModel.product = null;
            responseModel.message = "Please provide product image url";
        }

        else if(productCategoryError){
            responseModel.product = null;
            responseModel.message = "Please provide product category";
        }

        else{
            if(categoryService.getCategoryIds().contains(product.getCategoryId())){
                Product _product= new Product(product.getProductName(), product.getProductImageUrl(),product.getQty(),product.getDiscount(),product.getCategoryId());
                try{
                    Product result =  productRepository.save(_product);
                    responseModel.product = result;
                    responseModel.message = "Successfully saved";
                }
                catch (Exception e){
                    logger.info("MongoDb Connection Error"+e);
                    responseModel.product = null;
                    responseModel.message = "Try again, Error in connection established with Mongo DB";
                }
            }

            else{
                responseModel.product = null;
                responseModel.message = "Invalid category Id";
            }
        }

        return responseModel;
    }

    @Override
    public ProductResponseModel update(String productId, Product pro) {
        ProductResponseModel responseModel = new ProductResponseModel();
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Product _product = product.get();
            if(pro.getProductName() != ""){
                _product.setProductName(pro.getProductName());
            }
            if(pro.getProductImageUrl() != ""){
                _product.setProductImageUrl(pro.getProductImageUrl());
            }
            if(pro.getQty() != 0){
                _product.setQty(pro.getQty());
            }
            if(pro.getDiscount() != 0 || pro.getDiscount() != null){
                _product.setDiscount(pro.getDiscount());
            }
            if(pro.getCategoryId() != ""){
                _product.setCategoryId(pro.getCategoryId());
            }
            if(categoryService.getCategoryIds().contains(pro.getCategoryId())) {
                try {
                    Product result = productRepository.save(_product);
                    responseModel.product = result;
                    responseModel.message = "Successfully Updated!";
                } catch (Exception e) {
                    logger.info("MongoDb Connection Error" + e);
                    responseModel.product = null;
                    responseModel.message = "Try again, Error in connection established with Mongo DB";
                }
            }
            else{
                responseModel.product = null;
                responseModel.message = "Invalid category Id";
            }
        } else {
            responseModel.product = null;
            responseModel.message = "No product found with provided Id!";
        }
        return responseModel;
    }

    @Override
    public ProductResponseModel getById(String productId) {
        ProductResponseModel responseModel = new ProductResponseModel();
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            responseModel.product = product.get();
            responseModel.message = "";
        }
        else {
            responseModel.product = null;
            responseModel.message = "No product found with provided Id!";
        }

        return responseModel;
    }

    @Override
    public ProductResponseModel delete(String productId) {
        ProductResponseModel responseModel = new ProductResponseModel();
        try{
            productRepository.deleteById(productId);
            responseModel.product = null;
            responseModel.message = "Deleted successfully!";
        }
        catch (Exception e){
            logger.info("MongoDb Connection Error"+e);
            responseModel.product = null;
            responseModel.message = "Try again, Error in connection established with Mongo DB";
        }
        return responseModel;
    }
}
