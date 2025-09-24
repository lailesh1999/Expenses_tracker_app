package com.expense_tracker.models;

import com.google.gson.annotations.SerializedName;

public class category {
    @SerializedName("CategoryId")
    private String categoryId;

    @SerializedName("UserId")
    private String userId;

    @SerializedName("CategoryName")
    private String categoryName;

    @SerializedName("CategoryType")
    private String categoryType;

    // Constructor
    public category(String categoryId, String userId, String categoryName, String categoryType) {
        this.categoryId = categoryId;
        this.userId = userId;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    // Empty constructor
    public category() {}

    // Getters and Setters
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}