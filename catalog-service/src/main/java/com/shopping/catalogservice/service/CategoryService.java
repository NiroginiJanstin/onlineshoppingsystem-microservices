package com.shopping.catalogservice.service;

import com.mongodb.BasicDBObject;
import com.shopping.catalogservice.entity.Category;
import com.shopping.catalogservice.entity.Product;
import com.shopping.catalogservice.model.CategoryResponseModel;
import com.shopping.catalogservice.repository.ICategoryRepository;
import com.shopping.catalogservice.utils.validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public CategoryResponseModel create(Category category) {
        CategoryResponseModel responseModel = new CategoryResponseModel();
        boolean categoryNameError = validation.emptyFieldValidation(category.getCategoryName());
        boolean categoryImageUrlError = validation.emptyFieldValidation(category.getCategoryImageUrl());
        if (categoryNameError) {
            responseModel.category = null;
            responseModel.message = "Please provide category name";
        } else if (categoryImageUrlError) {
            responseModel.category = null;
            responseModel.message = "Please provide category image url";
        } else {
            Category _category = new Category(category.getCategoryName(), category.getCategoryImageUrl());
            try {
                Category result = categoryRepository.save(_category);
                responseModel.category = result;
                responseModel.message = "Successfully saved";
            } catch (Exception e) {
                logger.info("MongoDb Connection Error" + e);
                responseModel.category = null;
                responseModel.message = "Try again, Error in connection established with Mongo DB";
            }
        }

        return responseModel;
    }

    public CategoryResponseModel update(String categoryId, Category cat) {
        CategoryResponseModel responseModel = new CategoryResponseModel();
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category _category = category.get();
            if (cat.getCategoryName() != "") {
                _category.setCategoryName(cat.getCategoryName());
            }
            if (cat.getCategoryImageUrl() != "") {
                _category.setCategoryImageUrl(cat.getCategoryImageUrl());
            }
            try {
                Category result = categoryRepository.save(_category);
                responseModel.category = result;
                responseModel.message = "Successfully Updated!";
            } catch (Exception e) {
                logger.info("MongoDb Connection Error" + e);
                responseModel.category = null;
                responseModel.message = "Try again, Error in connection established with Mongo DB";
            }

        } else {
            responseModel.category = null;
            responseModel.message = "No category found with provided Id!";
        }
        return responseModel;
    }

    public CategoryResponseModel getById(String categoryId) {
        CategoryResponseModel responseModel = new CategoryResponseModel();
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            responseModel.category = category.get();
            responseModel.message = "";
        } else {
            responseModel.category = null;
            responseModel.message = "No category found with provided Id!";
        }

        return responseModel;
    }

    @Override
    public CategoryResponseModel delete(String categoryId) {
        CategoryResponseModel responseModel = new CategoryResponseModel();
        try {
            categoryRepository.deleteById(categoryId);
            responseModel.category = null;
            responseModel.message = "Deleted successfully!";
        } catch (Exception e) {
            logger.info("MongoDb Connection Error" + e);
            responseModel.category = null;
            responseModel.message = "Try again, Error in connection established with Mongo DB";
        }
        return responseModel;
    }

    public List<String> getCategoryIds() {
        List<String> ids = new ArrayList<String>();
        categoryRepository.findAll().forEach(category -> ids.add(category.getCategoryId()));
        return ids;
    }

    public String deleteAll() {
        categoryRepository.deleteAll();
        return "All categories are deleted successfully!";
    }
}
