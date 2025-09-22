package com.expense_tracker.data.repository;

import com.expense_tracker.data.remote.ApiServices;
import com.expense_tracker.data.remote.RetrofitHelper;
import com.expense_tracker.models.LoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;

public class userRepository {
    private ApiServices apiService;

    public userRepository(){
        apiService = RetrofitHelper.getRetrofitInstance().create(ApiServices.class);
    }

    public Call<LoginResponse> Login(JsonObject body){
       return apiService.login(body);
    }





}
