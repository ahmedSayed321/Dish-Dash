package com.example.dishdash.category.data.repo;

import com.example.dishdash.category.data.model.Category;

import java.util.List;

public interface CategoryRepo {

    public void onSuccess(List<Category> categoryList);
    public void onFailure(String message);
}
