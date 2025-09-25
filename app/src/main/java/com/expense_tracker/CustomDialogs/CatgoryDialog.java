package com.expense_tracker.CustomDialogs;

import static android.widget.Toast.LENGTH_LONG;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;

import com.expense_tracker.listener.OnCategoryUpdatedListener;
import com.expense_tracker.R;
import com.expense_tracker.models.category;
import com.expense_tracker.viewmodel.CategoryViewModel;
import com.google.gson.JsonObject;

public class CatgoryDialog extends Dialog {

    private String CategoryName,CategoryType;
    EditText Categorytxt;
    Context context;
    TextView headerTxt;
    Spinner CategeryTypeSpinner;
    Button save,cancel;
    category category;
    private int position;

    CategoryViewModel categoryViewModel;

    private OnCategoryUpdatedListener listener;

    public CatgoryDialog(Context context, String CategoryName, String CategoryType, category category,OnCategoryUpdatedListener listener,int position) {
        super(context);
        this.CategoryName = CategoryName;
        this.CategoryType = CategoryType;
        this.context = context;
        this.category = category;
        this.listener = listener;
        this.position = position;
    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_dialog);
        categoryViewModel = new CategoryViewModel();
        headerTxt = findViewById(R.id.dialog_title);
        Categorytxt = findViewById(R.id.category_name_edit);
        CategeryTypeSpinner = findViewById(R.id.category_type_spinner);
        save = findViewById(R.id.save_button);
        cancel = findViewById(R.id.cancel_button);
        headerTxt.setText("Category");
        Categorytxt.setText(CategoryName);
        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) CategeryTypeSpinner.getAdapter();
        if (CategoryType != null) {
            int spinnerPosition = adapter.getPosition(CategoryType);
            if (spinnerPosition >= 0) {
                CategeryTypeSpinner.setSelection(spinnerPosition);
            }
        }

        save.setOnClickListener(v->{
            category.setCategoryName(Categorytxt.getText().toString().trim());
            category.setCategoryType(CategeryTypeSpinner.getSelectedItem().toString().trim());
            JsonObject data = new JsonObject();
            data.addProperty("CategoryId",category.getCategoryId());
            data.addProperty("CategoryName",Categorytxt.getText().toString().trim());
            data.addProperty("CategoryType",CategeryTypeSpinner.getSelectedItem().toString().trim());
            updateCategory(data,context,category,position);
            dismiss();
        });

        cancel.setOnClickListener(v->{
            dismiss();
        });


    }

    public void updateCategory(JsonObject data,Context context,category category,int position){
        categoryViewModel.updateCategory(data).observe((LifecycleOwner)context, response->{
            if(response.getMessage() != null){
                //Toast.makeText(context,response.getMessage(),LENGTH_LONG).show();
                if(listener != null){
                    listener.onCategoryUpdate(category,position);
                }
            }
            else{
                Toast.makeText(context,response.getMessage(),LENGTH_LONG).show();
            }
        });
    }
}
