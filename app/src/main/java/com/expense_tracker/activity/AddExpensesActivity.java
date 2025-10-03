package com.expense_tracker.activity;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.expense_tracker.R;
import com.expense_tracker.models.CategoryTypeName;
import com.expense_tracker.models.CategoryTypeNameList;
import com.expense_tracker.viewmodel.CategoryViewModel;
import com.expense_tracker.viewmodel.ExpensesViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExpensesActivity extends AppCompatActivity {

    CategoryViewModel viewModel;

    EditText dataPicker, titleTxt, amountTxt, notesTxt;
    Button addExpenses;

    List<CategoryTypeName> categoryTypeNameList = new ArrayList<>();

    ArrayAdapter<CategoryTypeName> arrayAdapter;
    Spinner categoryTypeSpinner;

    ExpensesViewModel expensesViewModel;
    Calendar calander;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expenses);
        viewModel = new CategoryViewModel();
        categoryTypeSpinner = findViewById(R.id.category_spinner);
        dataPicker = findViewById(R.id.dateTxt);
        notesTxt = findViewById(R.id.notesTxt);
        titleTxt = findViewById(R.id.titleTxt);
        amountTxt = findViewById(R.id.amountTxt);
        addExpenses = findViewById(R.id.add_expenses_button);


        calander = Calendar.getInstance();
        dataPicker.setOnClickListener(v -> {
            int year = calander.get(Calendar.YEAR);
            int month = calander.get(Calendar.MONTH);
            int day = calander.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddExpensesActivity.this,
                    (view, selectYear, selectedMonth, selecedDay) -> {
                        String date = String.format("%02d-%02d-%d", selectYear, (selectedMonth + 1), selecedDay);
                        dataPicker.setText(date);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryTypeNameList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTypeSpinner.setAdapter(arrayAdapter);
        setCategoryTypeSpinner();

        expensesViewModel = new ExpensesViewModel();
        addExpenses.setOnClickListener(v->{
            CategoryTypeName selectedName = (CategoryTypeName) categoryTypeSpinner.getSelectedItem();
            if (selectedName == null) {
                Toast.makeText(this, "Please select a category", Toast.LENGTH_SHORT).show();
                return;
            }
            JsonObject jsonData = new JsonObject();
            jsonData.addProperty("UserId","101");
            jsonData.addProperty("Title",titleTxt.getText().toString().trim());
            jsonData.addProperty("Amount",amountTxt.getText().toString().trim());
            jsonData.addProperty("Notes",notesTxt.getText().toString().trim());
            jsonData.addProperty("CategoryId",selectedName.getCategoryId());
            jsonData.addProperty("ExpenseDate",dataPicker.getText().toString().trim());
            addExpenses(jsonData);
        });

    }

    private void setCategoryTypeSpinner() {
        viewModel.getAllCategoryName("101").observe(this, response -> {
            categoryTypeNameList.clear();
            categoryTypeNameList.addAll(response);
            arrayAdapter.notifyDataSetChanged();
        });
    }

    private void addExpenses(JsonObject data) {
        expensesViewModel.addExpenses(data).observe(this,response->{
            Toast.makeText(this,response.getMessage(),LENGTH_LONG).show();
        });

    }
}