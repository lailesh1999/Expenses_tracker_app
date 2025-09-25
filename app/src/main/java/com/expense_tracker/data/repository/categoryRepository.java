package com.expense_tracker.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.remote.ApiServices;
import com.expense_tracker.data.remote.RetrofitHelper;
import com.expense_tracker.models.APIResponse;
import com.expense_tracker.models.CategoryResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoryRepository {

    private ApiServices apiServices;

    private MutableLiveData<CategoryResponse> categories = new MutableLiveData<>();

    private MutableLiveData<APIResponse> deleteCategoryResponse = new MutableLiveData<>();

    public categoryRepository(){
        apiServices = RetrofitHelper.getRetrofitInstance().create(ApiServices.class);
    }

    public Call<APIResponse> addCategory(JsonObject data){
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

    public LiveData<APIResponse> deleteCategory(JsonObject jsonBody){
    Call<APIResponse> call = apiServices.deleteCategory(jsonBody);
    call.enqueue(new Callback<APIResponse>() {
        @Override
        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            if(response.isSuccessful() && response.body() != null){
                deleteCategoryResponse.setValue(response.body());
            }else{
                APIResponse error = new APIResponse();
                error.setMessage("Delete failed: " + response.code());
                deleteCategoryResponse.setValue(error);
            }
        }

        @Override
        public void onFailure(Call<APIResponse> call, Throwable t) {
            APIResponse error = new APIResponse();
            error.setMessage("Delete failed: " + t.getMessage());
            deleteCategoryResponse.setValue(error);
        }
        });
        return deleteCategoryResponse;
    }

    public  LiveData<APIResponse> updateCategory(JsonObject data){
        Call<APIResponse> call = apiServices.updateCatgory(data);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                if(response.body() != null && response.isSuccessful()) {
                    deleteCategoryResponse.setValue(response.body());
                }else{
                    APIResponse error = new APIResponse();
                    error.setMessage("Update  failed: " + response.code());
                    deleteCategoryResponse.setValue(error);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                APIResponse error = new APIResponse();
                error.setMessage("Delete failed: " + t.getMessage());
                deleteCategoryResponse.setValue(error);
            }
        });

        return  deleteCategoryResponse;
    }



}
