package com.shopping.catalogservice.service;

import com.shopping.catalogservice.entity.Category;
import com.shopping.catalogservice.model.CategoryResponseModel;

import java.util.List;

public interface ICategoryService {
    public CategoryResponseModel create(Category category);
    public CategoryResponseModel update(String categoryId, Category category);
    public CategoryResponseModel getById(String categoryId);
    public CategoryResponseModel delete(String categoryId);
    public List<String> getCategoryIds();
    public String deleteAll();
    public List<Category> getAll();
}
