package com.example.ead_frontend_android;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend_android.R;
import com.example.ead_frontend_android.Response.UserResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;
import com.example.ead_frontend_android.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePage extends AppCompatActivity {
    private EditText editName;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editNic;
    private EditText editPhoneNumber;
    private Button saveButton;
    private String userId,username,userEmail,userPassword,userNIC,userPhone,userType;
    boolean userIsactive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofile);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarupdate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");

        editName = findViewById(R.id.name_input_field);
        editEmail = findViewById(R.id.email_input_field);
        editPassword = findViewById(R.id.password_input_field);
        editNic = findViewById(R.id.nic_input_field);
        editPhoneNumber = findViewById(R.id.phone_input_field);
        saveButton = findViewById(R.id.save_button);

        // Retrieve the data from the Intent
        Intent intent = getIntent();
      userId = intent.getStringExtra("id");
        username = intent.getStringExtra("username");
      userEmail = intent.getStringExtra("userEmail");
      userPassword = intent.getStringExtra("userPassword");
      userNIC = intent.getStringExtra("userNIC");
   userPhone = intent.getStringExtra("userPhone");
   userType = intent.getStringExtra("userType");
    userIsactive = intent.getBooleanExtra("userIsactive",userIsactive);



        editName.setText(username);
        editPassword.setText(userEmail);
        editEmail.setText(userEmail);
        editPassword.setText(userPassword);
        editNic.setText(userNIC);
        editPhoneNumber.setText(userPhone);



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user-entered details
                String updatedName = editName.getText().toString();
                String updatedEmail = editEmail.getText().toString();
                String updatedPassword = editPassword.getText().toString();
                String updatedNic = editNic.getText().toString();
                String updatedPhoneNumber = editPhoneNumber.getText().toString();

                // Create a User object with the updated details
                User updatedUser = new User();
//                updatedUser.setId(userId);
                updatedUser.setName(updatedName);
                updatedUser.setEmail(updatedEmail);
                updatedUser.setPassword(updatedPassword);
                updatedUser.setNic(updatedNic);
                updatedUser.setPhone(updatedPhoneNumber);
                updatedUser.setUserType(userType);
                updatedUser.setActive(userIsactive);
                Log.d(TAG, "comedata  " + updatedUser.toString());
                // Create a Retrofit call to update the user details
                Call<UserResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).UpdateUser(userId, updatedUser);

                // Enqueue the call to send the update request
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Log.d(TAG, "comedata2  " + updatedUser.toString());
                        if (response.isSuccessful()) {
                            Log.d(TAG, "comedata3  " + updatedUser.toString());
                            UserResponse userResponse = response.body();
                            if (userResponse != null) {
                                // User details were successfully updated
                                Log.d(TAG, "onResponse: User details updated: " + userResponse.toString());

                                // Provide feedback to the user (e.g., a success message)
//                                Intent intent = new Intent(EditProfilePage.this, ProfileFragment.class);
                                Intent intent = new Intent(EditProfilePage.this, ProfileFragment.class);
                                startActivity(intent);
                                // Start the signup activity
                                Toast.makeText(EditProfilePage.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();


                                // Start the signup activity

                                // You can finish this activity or navigate to another page
                                // finish();
                            }
                        } else {
                            // Update request was unsuccessful
                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
                            Log.d(TAG, "onResponse: Response Body: " + response.message());

                            // Provide an error message to the user
                            Toast.makeText(EditProfilePage.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        // Log the failure message
                        Log.d(TAG, "onFailure: " + t.getMessage());

                        // Provide an error message to the user
                        Toast.makeText(EditProfilePage.this, "Profile update failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
