package com.expense_tracker.viewmodel;

import androidx.lifecycle.LiveData;

import com.expense_tracker.data.repository.ExpensesRepository;
import com.expense_tracker.models.APIResponse;
import com.google.gson.JsonObject;

public class ExpensesViewModel {
    ExpensesRepository repo;
    public  ExpensesViewModel(){
        repo = new ExpensesRepository();
    }
    public  LiveData<APIResponse> addExpenses(JsonObject data){
        return  repo.addExpenses(data);
    }



}
