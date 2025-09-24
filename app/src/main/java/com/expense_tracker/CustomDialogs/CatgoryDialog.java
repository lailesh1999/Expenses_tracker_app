package com.expense_tracker.CustomDialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.expense_tracker.R;

public class CatgoryDialog extends Dialog {

    private String CategoryName,CategoryType;
    EditText Categorytxt;
    Context context;
    TextView headerTxt;
    Spinner CategeryTypeSpinner;
    Button save,cancel;

    public CatgoryDialog(Context context,String CategoryName,String CategoryType) {
        super(context);
        this.CategoryName = CategoryName;
        this.CategoryType = CategoryType;
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_dialog);
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
            dismiss();
        });

        cancel.setOnClickListener(v->{
            dismiss();
        });

    }
}
