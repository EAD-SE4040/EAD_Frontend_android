package com.example.ead_frontend_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextPassword, editTextNIC, editTextPhone;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextNIC = findViewById(R.id.editTextNIC);
        editTextPhone = findViewById(R.id.editTextPhone);
        btnSignUp = findViewById(R.id.btnSignUp);







        // Set a click listener for the Sign Up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String nic = editTextNIC.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                // Define the Intent to navigate to the signup page
                Intent intent = new Intent(SignUp.this, MainActivity.class);

                // Start the signup activity
                startActivity(intent);




            }
        });

    }

}
