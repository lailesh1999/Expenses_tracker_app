package com.expense_tracker.data.remote;

import com.expense_tracker.constants.constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {


    private static Retrofit retrofit;

    public static  Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(constants.BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }


}
