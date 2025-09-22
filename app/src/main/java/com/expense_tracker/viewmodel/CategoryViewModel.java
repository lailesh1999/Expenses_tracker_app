package com.expense_tracker.viewmodel;

import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.repository.categoryRepository;
import com.expense_tracker.models.DataInsertResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel {

    private categoryRepository catRepo;

    public CategoryViewModel(){
        catRepo =  new categoryRepository();
    }

    public void addCategory(JsonObject data, onApiResponse<DataInsertResponse> onApiResponse){
        Call<DataInsertResponse> call = catRepo.addCategory(data);
        call.enqueue(new Callback<DataInsertResponse>() {
            @Override
            public void onResponse(Call<DataInsertResponse> call, Response<DataInsertResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    onApiResponse.onSuccess(response.body());
                }else {
                    onApiResponse.onFailure("Response unsuccessful");
                }
            }
            @Override
            public void onFailure(Call<DataInsertResponse> call, Throwable t) {
                onApiResponse.onFailure(t.getMessage());
            }
        });
    }


}
