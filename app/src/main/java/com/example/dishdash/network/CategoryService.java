package com.example.dishdash.network;

import com.example.dishdash.category.data.model.CategoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
   @GET("categories.php")
   Call<CategoriesResponse> getCategories();
}
