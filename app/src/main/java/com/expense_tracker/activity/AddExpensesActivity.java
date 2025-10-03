package com.expense_tracker.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.expense_tracker.R;
import com.expense_tracker.models.CategoryTypeName;
import com.expense_tracker.models.CategoryTypeNameList;
import com.expense_tracker.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddExpensesActivity extends AppCompatActivity {

    CategoryViewModel viewModel;

    List<CategoryTypeName> categoryTypeNameList = new ArrayList<>();

    ArrayAdapter<CategoryTypeName> arrayAdapter;
    Spinner categoryTypeSpinner;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);
        viewModel = new CategoryViewModel();
        categoryTypeSpinner = findViewById(R.id.category_spinner);
       arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categoryTypeNameList);
       arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       categoryTypeSpinner.setAdapter(arrayAdapter);
        setCategoryTypeSpinner();

    }

    private void setCategoryTypeSpinner(){
        viewModel.getAllCategoryName("101").observe(this,response->{
            categoryTypeNameList.clear();
            categoryTypeNameList.addAll(response);
            arrayAdapter.notifyDataSetChanged();
        });
    }
}