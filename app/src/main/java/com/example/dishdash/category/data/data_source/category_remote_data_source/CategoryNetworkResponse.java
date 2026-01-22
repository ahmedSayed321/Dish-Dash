package com.example.dishdash.category.data.data_source.category_remote_data_source;

import com.example.dishdash.category.data.model.Category;

import java.util.List;

public interface CategoryNetworkResponse {
    void onSuccess(List<Category> productList);
    void onFailure(String message);

}
