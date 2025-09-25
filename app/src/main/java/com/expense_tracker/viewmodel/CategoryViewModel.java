package com.expense_tracker.viewmodel;

import androidx.lifecycle.LiveData;

import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.repository.categoryRepository;
import com.expense_tracker.models.APIResponse;
import com.expense_tracker.models.CategoryResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel {

    private categoryRepository catRepo;

    public CategoryViewModel(){
        catRepo =  new categoryRepository();
    }

    public void addCategory(JsonObject data, onApiResponse<APIResponse> onApiResponse){
        Call<APIResponse> call = catRepo.addCategory(data);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    onApiResponse.onSuccess(response.body());
                }else {
                    onApiResponse.onFailure("Response unsuccessful");
                }
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                onApiResponse.onFailure(t.getMessage());
            }
        });
    }

    public LiveData<CategoryResponse> getAllCategory(String userId){
        return  catRepo.getAllCategory(userId);
    }

    public LiveData<APIResponse> deleteCategory(JsonObject data){
        return  catRepo.deleteCategory(data);
    }

    public LiveData<APIResponse> updateCategory(JsonObject data){
        return  catRepo.updateCategory(data);
    }






}
