package com.expense_tracker.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.expense_tracker.R;
import com.expense_tracker.models.CategoryTypeName;
import com.expense_tracker.models.CategoryTypeNameList;
import com.expense_tracker.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExpensesActivity extends AppCompatActivity {

    CategoryViewModel viewModel;

    EditText dataPicker;


    List<CategoryTypeName> categoryTypeNameList = new ArrayList<>();

    ArrayAdapter<CategoryTypeName> arrayAdapter;
    Spinner categoryTypeSpinner;
    Calendar calander;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);
        viewModel = new CategoryViewModel();
        categoryTypeSpinner = findViewById(R.id.category_spinner);
        dataPicker = findViewById(R.id.dateTxt);
        calander = Calendar.getInstance();
        dataPicker.setOnClickListener(v->{
            int year = calander.get(Calendar.YEAR);
            int month = calander.get(Calendar.MONTH);
            int day = calander.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddExpensesActivity.this,
                    (view,selectYear,selectedMonth,selecedDay)->{
                        String date = String.format("%02d-%02d-%d",selecedDay,(selectedMonth + 1),selectYear);
                        dataPicker.setText(date);
                    },
                    year,month,day
            );
            datePickerDialog.show();
        });
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