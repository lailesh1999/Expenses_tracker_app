package com.expense_tracker.activity;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.expense_tracker.R;
import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.local.PreferenceManager;
import com.expense_tracker.models.CategoryResponse;
import com.expense_tracker.models.DataInsertResponse;
import com.expense_tracker.models.category;
import com.expense_tracker.viewmodel.CategoryViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private Button addCategory;
    private EditText categoryName;
     private Spinner categoryType;
     private CategoryViewModel categoryViewModel;
     private List<category> categoryList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);
        addCategory = findViewById(R.id.add_category_button);
        categoryName = findViewById(R.id.category_name);
        categoryType = findViewById(R.id.category_type_spinner);
        categoryViewModel = new CategoryViewModel();
        Log.d("aa", PreferenceManager.getKeyUserId(CategoryActivity.this));
        addCategory.setOnClickListener(view -> {
            JsonObject jsonBody = new JsonObject();
            jsonBody.addProperty("UserId", PreferenceManager.getKeyUserId(CategoryActivity.this));
            jsonBody.addProperty("CategoryName",categoryName.getText().toString().trim());
            jsonBody.addProperty("CategoryType",categoryType.getSelectedItem().toString().trim());
            categoryViewModel.addCategory(jsonBody, new onApiResponse<DataInsertResponse>() {
                @Override
                public void onSuccess(DataInsertResponse dataInsertResponse) {
                    Toast.makeText(CategoryActivity.this,dataInsertResponse.getMessage(), LENGTH_LONG).show();
                }
                @Override
                public void onFailure(String message) {
                    Toast.makeText(CategoryActivity.this,message, LENGTH_LONG).show();
                }
            });
        });
        categoryViewModel.getAllCategory("101").observe(this, response->{
            if(response.getCategories() != null){
                // clear previous data to avoid duplicates
                categoryList.clear();
                categoryList.addAll(response.getCategories());
            }
            else{
                Toast.makeText(this,"No data Found ",LENGTH_LONG).show();
            }
        } );

    }
}