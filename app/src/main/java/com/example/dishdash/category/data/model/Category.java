package com.example.dishdash.category.data.model;

import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("idCategory")
    private String categoryId;

    @SerializedName("strCategory")
    private String categoryName;

    @SerializedName("strCategoryThumb")
    private String categoryImage;

    public Category(String categoryId, String categoryName, String categoryImage) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }

    @SerializedName("strCategoryDescription")
    private String strCategoryDescription;

}
