package com.example.dishdash.category.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriesResponse {

    @SerializedName("categories")
    public List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
