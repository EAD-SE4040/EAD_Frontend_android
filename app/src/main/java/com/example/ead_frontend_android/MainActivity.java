package com.example.ead_frontend_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import com.example.ead_frontend_android.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String userId,userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if there are extras in the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Retrieve the data using the keys you used when you added them in the Login activity
             userId = extras.getString("userId");
             userType = extras.getString("userType");

            // Now, you can use the userId and name as needed in your MainActivity
            // For example, you can display them in TextViews or manipulate your UI accordingly.
        }
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userId = preferences.getString("userId", ""); // Provide a default value in case the key is not found
        String name = preferences.getString("usertype", "");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        if ("traveler".equals(userType)) {
            replaceFragment(new HomeFragment());
        } else {
            // Handle the case when the user is not a traveler.
            replaceFragment(new ProfileFragment());
        }

        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home111:
                    if ("traveler".equals(userType)) {
                        replaceFragment(new HomeFragment());
                    } else {
                        showMessageToChangeRole();
                    }
                    break;
                case R.id.booking111:
                    if ("traveler".equals(userType)) {
                        replaceFragment(new MyBookingFragment());
                    } else {
                        showMessageToChangeRole();
                    }
                    break;
                case R.id.profile111:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });

    }

    private void showMessageToChangeRole() {

        Snackbar snackbar = Snackbar.make(binding.getRoot(), "Access denied. Please switch to the 'traveler' role to continue.", Snackbar.LENGTH_SHORT);


        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.customSnackbarBackground));

// Show the Snackbar
        snackbar.show();

        // Or you can use a Toast:
        // Toast.makeText(this, "Please change your role to 'traveler'", Toast.LENGTH_SHORT).show();
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}



