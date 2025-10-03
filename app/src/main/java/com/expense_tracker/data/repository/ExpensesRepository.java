package com.expense_tracker.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.expense_tracker.data.remote.ApiServices;
import com.expense_tracker.data.remote.RetrofitHelper;
import com.expense_tracker.models.APIResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesRepository {
    ApiServices apiServices;

    MutableLiveData<APIResponse> insertResponse = new MutableLiveData<>();
    public  ExpensesRepository(){
        apiServices = RetrofitHelper.getRetrofitInstance().create(ApiServices.class);
    }

    public LiveData<APIResponse> addExpenses(JsonObject data){
        Call<APIResponse> response = apiServices.addExpenses(data);
        response.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    insertResponse.setValue(response.body());
                }else{
                    APIResponse api = new APIResponse();
                    api.setMessage("Error unable to insert");
                    insertResponse.setValue(api);
                }
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                APIResponse api = new APIResponse();
                api.setMessage(t.getMessage());
                insertResponse.setValue(api);
            }
        });

        return  insertResponse;

    }



}
