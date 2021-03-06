package com.shopping.catalogservice.service;

import com.mongodb.BasicDBObject;
import com.shopping.catalogservice.entity.Product;
import com.shopping.catalogservice.model.ProductResponseModel;
import com.shopping.catalogservice.repository.IProductRepository;
import com.shopping.catalogservice.utils.validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    IProductRepository productRepository;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ProductResponseModel create(Product product) {
        ProductResponseModel responseModel =  new ProductResponseModel();
        boolean productNameError = validation.emptyFieldValidation(product.getProductName());
        boolean productImageUrlError = validation.emptyFieldValidation(product.getProductImageUrl());
        boolean productPriceError = product.getPrice() == null ? true : false;
        boolean productCategoryError = validation.emptyFieldValidation(product.getCategoryId());

        if(productNameError){
            responseModel.product = null;
            responseModel.message = "Please provide product name";
        }

        else if(productImageUrlError){
            responseModel.product = null;
            responseModel.message = "Please provide product image url";
        }

        else if(productPriceError){
            responseModel.product = null;
            responseModel.message = "Please provide product price";
        }

        else if(productCategoryError){
            responseModel.product = null;
            responseModel.message = "Please provide product category";
        }

        else{
            if(categoryService.getCategoryIds().contains(product.getCategoryId())){
                Product _product= new Product(product.getProductName(), product.getProductImageUrl(),product.getQty(),product.getPrice(),product.getDiscount(),product.getCategoryId());
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
            if(pro.getPrice() != null){
                _product.setPrice(pro.getPrice());
            }
            if(pro.getDiscount() != null){
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

    @Override
    public List<Product> getProductsByCategoryId(String categoryId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("categoryId").is(categoryId));
        List<Product> products = mongoTemplate.find(query, Product.class);
        return products;
    }
}
