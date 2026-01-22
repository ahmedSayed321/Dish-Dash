package com.example.dishdash.data.repo.meals;

import com.example.dishdash.data.model.meals.Category;

import java.util.List;

public interface CategoryRepo {

    public void onSuccess(List<Category> categoryList);
    public void onFailure(String message);
}
