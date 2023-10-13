package com.example.ead_frontend_android;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_frontend_android.Response.UserResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;
import com.example.ead_frontend_android.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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



//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user input from EditText fields
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//                // Create a User object with the user's login data
//                User user = new User();
//                user.setEmail(email);
//                user.setPassword(password);
//
//                // Create a Retrofit call to send the login request
//                Call<UserResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).login(user);
//
//                // Enqueue the call to send the login request
//                call.enqueue(new Callback<UserResponse>() {
//                    @Override
//                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                        if (response.isSuccessful()) {
//                            UserResponse userResponse = response.body();
//                            if (userResponse != null) {
//                                // Login was successful
//                                Log.d(TAG, "onResponse: Login successful");
//
//                                // Store user data or handle the response as needed
//                                String userId = userResponse.getId();
//                                String name = userResponse.getName();
//
//                                // You can pass user data to the home page using an Intent
//                                Intent intent = new Intent(Login.this, MainActivity.class);
//                                intent.putExtra("userId", userId);
//                                intent.putExtra("name", name);
//
//                                startActivity(intent);
//                                finish();
//                            }
//                        } else {
//                            // Login request was unsuccessful
//                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
//                            Log.d(TAG, "onResponse: Response Body: " + response.toString());
//
//                            // You can provide an error message to the user here
//                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserResponse> call, Throwable t) {
//                        // Log the failure message
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//
//                        // You can provide an error message to the user here
//                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Create a User object with the user's login data
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);

                // Create a Retrofit call to send the login request
                Call<UserResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).login(user);

                // Enqueue the call to send the login request
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            // HTTP status code 200 (OK) - Successful login
                            UserResponse userResponse = response.body();
                            if (userResponse != null) {
                                // Login was successful
                                Log.d(TAG, "onResponse: Login successful");

                                // Store user data or handle the response as needed
                                String userId = userResponse.getId();
//                                String name = userResponse.getName();
                                String usertype = userResponse.getUserType();
                                // After a successful login
                                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("userId", userId);
                                editor.putString("usertype", usertype);

                                editor.apply();


                                // You can pass user data to the home page using an Intent
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("userId", userId);
                                intent.putExtra("userType", usertype);

                                startActivity(intent);
                                Toast.makeText(Login.this, "Login sucessfully ", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else if (response.code() == 401) {
                            // HTTP status code 401 (Unauthorized) - Authentication failure
                            // Display an error message to the user
                            Log.d(TAG, "onResponse: Unauthorized - Invalid email or password");
                            Toast.makeText(Login.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle other HTTP status codes as needed
                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
                            Log.d(TAG, "onResponse: Response Body: " + response.toString());

                            // Provide an error message to the user for other cases
                            Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        // Log the failure message
                        Log.d(TAG, "onFailure: " + t.getMessage());

                        // Provide an error message to the user
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        // Set a click listener for the Login button
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user input from EditText fields
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//                String userId = "6523cd075525fd50a711e8c5";
//                String rname = "sayanthan";
//                String rpassword = "gaws";
//                String rnic = "985467843v";
//                String rphone = "0765436799";
////                String usertype = "traveler ";
//
//                SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("userId", userId);
//                editor.putString("name", userId);
//                editor.putString("email", userId);
//                editor.putString("nic", userId);
//                editor.putString("password", userId);
//
//
//                editor.apply();
//
//                Intent intent=new Intent(Login.this,MainActivity.class);
//                startActivity(intent);
//
//
//            }
//        });

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