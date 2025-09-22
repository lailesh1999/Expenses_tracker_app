package com.expense_tracker.data.repository;

import com.expense_tracker.data.remote.ApiServices;
import com.expense_tracker.data.remote.RetrofitHelper;
import com.expense_tracker.models.DataInsertResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;

public class categoryRepository {

    private ApiServices apiServices;

    public categoryRepository(){
        apiServices = RetrofitHelper.getRetrofitInstance().create(ApiServices.class);
    }

    public Call<DataInsertResponse> addCategory(JsonObject data){
        return  apiServices.addCategory(data);
    }


}
