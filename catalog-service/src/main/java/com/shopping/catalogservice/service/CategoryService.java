package com.shopping.catalogservice.service;

import com.shopping.catalogservice.controller.CategoryController;
import com.shopping.catalogservice.entity.Category;
import com.shopping.catalogservice.model.CategoryResponseModel;
import com.shopping.catalogservice.repository.ICategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService{
    Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public CategoryResponseModel createNew(Category category) {
        CategoryResponseModel responseModel =  new CategoryResponseModel();

        if(category.getCategoryName() == ""){
            responseModel.category = null;
            responseModel.message = "Please provide category name";
        }

        else if(category.getCategoryImageUrl() == ""){
            responseModel.category = null;
            responseModel.message = "Please provide category image url";
        }

        else{
            Category _category= new Category(category.getCategoryName(), category.getCategoryImageUrl());
            try{
                Category result =  categoryRepository.save(_category);
                responseModel.category = result;
                responseModel.message = "Successfully saved";
            }
            catch (Exception e){
                logger.info("MongoDb Connection Error"+e);
                responseModel.category = null;
                responseModel.message = "Try again, Error in connection established with Mongo DB";
            }
        }

        return responseModel;
    }
}
