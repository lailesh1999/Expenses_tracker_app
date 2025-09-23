package com.expense_tracker.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.remote.ApiServices;
import com.expense_tracker.data.remote.RetrofitHelper;
import com.expense_tracker.models.CategoryResponse;
import com.expense_tracker.models.DataInsertResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoryRepository {

    private ApiServices apiServices;

    private MutableLiveData<CategoryResponse> categories = new MutableLiveData<>();

    public categoryRepository(){
        apiServices = RetrofitHelper.getRetrofitInstance().create(ApiServices.class);
    }

    public Call<DataInsertResponse> addCategory(JsonObject data){
        return  apiServices.addCategory(data);
    }



    public LiveData<CategoryResponse> getAllCategory(String userId){
        Call<CategoryResponse> call = apiServices.getCategories(userId);
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    categories.setValue(response.body());
                }else{
                    categories.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                categories.setValue(null);
            }
        });

        return categories;
    }



}
