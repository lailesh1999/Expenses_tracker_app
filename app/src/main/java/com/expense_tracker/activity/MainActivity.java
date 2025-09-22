package com.expense_tracker.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.expense_tracker.R;
import com.expense_tracker.fragments.HomeFragment;
import com.expense_tracker.fragments.NotesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {

    BottomNavigationView bottomView;
    Fragment selectedFragment = null;
    @SuppressLint({"MissingInflatedId", "CommitTransaction"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bottom_navigation_bar);

        bottomView = findViewById(R.id.bottom_nav);
        if( savedInstanceState == null){
            loadFragment(new HomeFragment());
        }
        bottomView.setOnItemSelectedListener(item->{

          if(item.getItemId() == R.id.home){
              loadFragment(new HomeFragment());
              return true;
          } else if (item.getItemId() == R.id.notes) {
              loadFragment(new NotesFragment());

          }
            return false;
        });

    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
    }
}