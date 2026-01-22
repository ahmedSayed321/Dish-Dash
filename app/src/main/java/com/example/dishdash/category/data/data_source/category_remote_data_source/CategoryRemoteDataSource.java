package com.example.dishdash.category.data.data_source.category_remote_data_source;

import com.example.dishdash.category.data.model.CategoriesResponse;
import com.example.dishdash.category.data.model.Category;
import com.example.dishdash.network.CategoryService;
import com.example.dishdash.network.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRemoteDataSource {

    public CategoryService categoryService;

    public CategoryRemoteDataSource() {
        categoryService = Network.getInstance().categoryService;
     }

     public void getCategories(CategoryNetworkResponse categoryNetworkResponse){

        categoryService.getCategories().enqueue(new Callback<CategoriesResponse>() {
            @Override
            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Category> categoryList = response.body().categories;
                    categoryNetworkResponse.onSuccess(categoryList);
                }
                else{
                    categoryNetworkResponse.onFailure("Failured to fetch categories");
                }
            }

            @Override
            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
                   categoryNetworkResponse.onFailure(t.getMessage());
            }
        });



     }

}
