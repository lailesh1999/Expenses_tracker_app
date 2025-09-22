package com.expense_tracker.viewmodel;

import androidx.annotation.NonNull;

import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.repository.userRepository;
import com.expense_tracker.models.LoginResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {
     private userRepository userRepo ;

     public  LoginViewModel(){
         userRepo = new userRepository();
     }

     public void Login(JsonObject body , onApiResponse<LoginResponse> callback){
         Call<LoginResponse> call = userRepo.Login(body);
         call.enqueue(new Callback<LoginResponse>() {
             @Override
             public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                    if(response.isSuccessful() && response.body() != null){
                        callback.onSuccess(response.body());
                    }else {
                        callback.onFailure("Response unsuccessful");
                    }
             }

             @Override
             public void onFailure(@NonNull Call<LoginResponse> call, Throwable t) {
                 callback.onFailure(t.getMessage());
             }
         });

     }
}
