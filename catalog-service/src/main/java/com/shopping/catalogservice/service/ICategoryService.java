package com.shopping.catalogservice.service;

import com.shopping.catalogservice.entity.Category;
import com.shopping.catalogservice.model.CategoryResponseModel;

public interface ICategoryService {
    public CategoryResponseModel createNew(Category category);
}
