package com.expense_tracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.expense_tracker.R;
import com.expense_tracker.data.callBacks.onApiResponse;
import com.expense_tracker.data.local.PreferenceManager;
import com.expense_tracker.models.LoginResponse;
import com.expense_tracker.viewmodel.LoginViewModel;
import com.google.gson.JsonObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText passwordTxt,emailTxt;

    LoginViewModel loginViewModel;
    Button loginBtn;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(this);
        loginViewModel = new LoginViewModel();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogin){
            email = emailTxt.getText().toString().trim();
            password = passwordTxt.getText().toString().trim();
            JsonObject json = new JsonObject();
            json.addProperty("username", email);
            json.addProperty("password", password);
            loginViewModel.Login(json, new onApiResponse<>() {
                @Override
                public void onSuccess(LoginResponse response) {
                    Toast.makeText(LoginActivity.this, response.getMessage() + " : ", Toast.LENGTH_LONG).show();
                    PreferenceManager.saveLoginData(LoginActivity.this,response.getUserID(),response.getUsername());
                    if(response.getUserID() != null) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }
                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(LoginActivity.this, "Error:eee " + errorMessage, Toast.LENGTH_LONG).show();
                    Log.d("loginBtn", errorMessage);
                }
            });
        }
    }
}