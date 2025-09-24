package com.expense_tracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("categories")
    private List<category> categories;

    public CategoryResponse() {}

    public CategoryResponse(List<category> categories) {
        this.categories = categories;
    }

    public List<category> getCategories() {
        return categories;
    }

    public void setCategories(List<category> categories) {
        this.categories = categories;
    }
}