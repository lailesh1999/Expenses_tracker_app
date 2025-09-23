package com.expense_tracker.data.remote;

import com.expense_tracker.models.CategoryResponse;
import com.expense_tracker.models.DataInsertResponse;
import com.expense_tracker.models.LoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    @POST("user/login.php")
    Call<LoginResponse>login(@Body JsonObject body);

    @POST("category/insertCategory.php")
    Call<DataInsertResponse> addCategory(@Body JsonObject data);


    @GET("category/getAllcategory.php")
    Call<CategoryResponse> getCategories(
            @Query("userId") String userId
    );

}
