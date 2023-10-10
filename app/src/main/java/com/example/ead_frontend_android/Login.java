package com.example.ead_frontend_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private Button btnLogin;
    private TextView textSignup_ex;

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize UI elements
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textSignup_ex = findViewById(R.id.textSignup_ex);





        // Set a click listener for the Login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String userId = "6523cd075525fd50a711e8c5";
                String rname = "sayanthan";
                String rpassword = "gaws";
                String rnic = "985467843v";
                String rphone = "0765436799";
//                String usertype = "traveler ";

                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userId", userId);
                editor.putString("name", userId);
                editor.putString("email", userId);
                editor.putString("nic", userId);
                editor.putString("password", userId);


                editor.apply();

                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);


            }
        });

        // Set an OnClickListener to the TextView
        textSignup_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Define the Intent to navigate to the signup page
                Intent intent = new Intent(Login.this, SignUp.class);

                // Start the signup activity
                startActivity(intent);
            }
        });

    }


}