package com.example.exampratice;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFirst = findViewById(R.id.btn_first);
        Button btnSecond = findViewById(R.id.btn_second);

        // Optional: Load FirstFragment by default
        loadFragment(new FirstFragment(), false);

        btnFirst.setOnClickListener(v -> loadFragment(new FirstFragment(), false));
        btnSecond.setOnClickListener(v -> loadFragment(new SecondFragment(), true));
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            ft.addToBackStack(null);   // allows back button to return to previous fragment
        }

        ft.commit();
    }
}