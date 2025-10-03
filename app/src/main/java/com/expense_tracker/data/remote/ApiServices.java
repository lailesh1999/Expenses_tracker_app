package com.expense_tracker.data.remote;

import com.expense_tracker.models.APIResponse;
import com.expense_tracker.models.CategoryResponse;
import com.expense_tracker.models.CategoryTypeName;
import com.expense_tracker.models.CategoryTypeNameList;
import com.expense_tracker.models.LoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("user/login.php")
    Call<LoginResponse>login(@Body JsonObject body);

    @POST("category/insertCategory.php")
    Call<APIResponse> addCategory(@Body JsonObject data);

    @GET("category/getAllcategory.php")
    Call<CategoryResponse> getCategories(
            @Query("userId") String userId
    );
    @HTTP(method = "DELETE", path = "category/deleteCategory.php", hasBody = true)
    Call<APIResponse> deleteCategory(@Body JsonObject data);

    @PUT("category/updateCategory.php")
    Call<APIResponse> updateCatgory(@Body JsonObject data);

    @GET("category/getCategoryByName.php")
    Call<CategoryTypeNameList> getAllCategoryTypename(
            @Query("userId") String userId
    );

    @POST("expenses/insertExpenses.php")
    Call<APIResponse> addExpenses(@Body JsonObject data);


}
