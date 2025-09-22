package com.expense_tracker.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.expense_tracker.R;
import com.expense_tracker.activity.CategoryActivity;
import com.expense_tracker.data.local.PreferenceManager;

import java.util.Calendar;
import java.util.TimeZone;


public class HomeFragment extends Fragment  {

    private TextView greeting;
    private Button btnAddCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_fragment, container, false);

        greeting = view.findViewById(R.id.greeting);
        btnAddCategory = view.findViewById(R.id.btnAddCategory);


        setGreetingMessage();
        btnAddCategory.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CategoryActivity.class);
            startActivity(intent);
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void setGreetingMessage(){
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Kolkata");
        Calendar calender = Calendar.getInstance(timeZone);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
         String greetingMessage;
        if (hour >= 5 && hour < 12) {
            greetingMessage = "Good Morning 🌅 ";
        } else if (hour >= 12 && hour < 16) {
            greetingMessage = "Good Afternoon ☀️ ";
        } else if (hour >= 16 && hour < 21) {
            greetingMessage = "Good Evening 🌇 ";
        } else {
            greetingMessage = "Happy Night 🌙 ";
        }
        greeting.setText(greetingMessage + PreferenceManager.getUsername(getContext()));
    }


}