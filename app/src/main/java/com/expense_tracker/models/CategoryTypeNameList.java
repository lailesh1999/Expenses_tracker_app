package com.expense_tracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryTypeNameList {

    @SerializedName("categories")
    List<CategoryTypeName> categoryTypeNameList;

    public List<CategoryTypeName> getCategoryTypeNameList() {
        return categoryTypeNameList;
    }

    public void setCategoryTypeNameList(List<CategoryTypeName> categoryTypeNameList) {
        this.categoryTypeNameList = categoryTypeNameList;
    }
}
