//package com.example.ead_frontend_android;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class SignUp extends AppCompatActivity {
//    private EditText editTextName, editTextEmail, editTextPassword, editTextNIC, editTextPhone;
//    private Button btnSignUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.signup_page);
//
//        // Initialize UI elements
//        editTextName = findViewById(R.id.editTextName);
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        editTextNIC = findViewById(R.id.editTextNIC);
//        editTextPhone = findViewById(R.id.editTextPhone);
//        btnSignUp = findViewById(R.id.btnSignUp);
//
//
//
//
//
//
//
//        // Set a click listener for the Sign Up button
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user input from EditText fields
//                String name = editTextName.getText().toString().trim();
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//                String nic = editTextNIC.getText().toString().trim();
//                String phone = editTextPhone.getText().toString().trim();
//
//                // Define the Intent to navigate to the signup page
//                Intent intent = new Intent(SignUp.this, MainActivity.class);
//
//                // Start the signup activity
//                startActivity(intent);
//
//
//
//
//            }
//        });
//
//    }
//
//}

package com.example.ead_frontend_android;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend_android.Response.UserResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;
import com.example.ead_frontend_android.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || nic.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                // Create a User object with the user's signup data
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setNic(nic);
                user.setPhone(phone);
                user.setUserType("user");


                // Create a Retrofit call to send the signup request
                Call<UserResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).signup(user);

                // Enqueue the call to send the signup request
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            // HTTP status code 200 (OK) - Successful signup
                            UserResponse userResponse = response.body();
                            if (userResponse != null) {
                                // Signup was successful
                                Toast.makeText(SignUp.this, "Signup successful", Toast.LENGTH_SHORT).show();
                                // You can navigate to the login page or home page as needed
                                Intent intent = new Intent(SignUp.this, Login.class);
                                startActivity(intent);
                            }
                        }
                        else if (response.code() == 400) {
                            // Handle the 400 Bad Request error here
                            Log.d(TAG, "onResponse: Bad Request (400)");

                            try {
                                // Extract the error message from the response body
                                String errorBody = response.errorBody().string();


                                // You can then use the error message to display it to the user
                                Toast.makeText(SignUp.this, errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                // Handle any error while extracting the error message
                                Toast.makeText(SignUp.this, "Error processing the response", Toast.LENGTH_SHORT).show();
                            }
                        }


                        else {
                            // Handle other HTTP status codes or provide error messages as needed
                            Toast.makeText(SignUp.this, "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        // Log the failure message
                        Toast.makeText(SignUp.this, "Signup failed: Please check your network connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            }
        });
    }

}

