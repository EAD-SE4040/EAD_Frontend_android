package com.example.ead_frontend_android;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
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
    private String userId; // You should get the user ID from somewhere


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


        editName.setText("sayanthan");
        editEmail.setText("saya@gmail.com");
        editPassword.setText("sayan");
        editNic.setText("992591641v");
        editPhoneNumber.setText("0763817093");

        // Load user details here (you should fetch user details from your API or storage)
//        loadUserDetails();

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get user-entered details
//                String updatedName = editName.getText().toString();
//                String updatedEmail = editEmail.getText().toString();
//                String updatedPassword = editPassword.getText().toString();
//                String updatedNic = editNic.getText().toString();
//                String updatedPhoneNumber = editPhoneNumber.getText().toString();
//
//                // Create a User object with the updated details
//                User updatedUser = new User();
////                updatedUser.setId(userId);
//                updatedUser.setName(updatedName);
//                updatedUser.setEmail(updatedEmail);
//                updatedUser.setPassword(updatedPassword);
//                updatedUser.setNic(updatedNic);
//                updatedUser.setPhone(updatedPhoneNumber);
//
//                // Create a Retrofit call to update the user details
//                Call<UserResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).UpdateUser(userId, updatedUser);
//
//                // Enqueue the call to send the update request
//                call.enqueue(new Callback<UserResponse>() {
//                    @Override
//                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
//                        if (response.isSuccessful()) {
//                            UserResponse userResponse = response.body();
//                            if (userResponse != null) {
//                                // User details were successfully updated
//                                Log.d(TAG, "onResponse: User details updated: " + userResponse.toString());
//
//                                // Provide feedback to the user (e.g., a success message)
//                                Toast.makeText(EditProfilePage.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
//
//                                // You can finish this activity or navigate to another page
//                                // finish();
//                            }
//                        } else {
//                            // Update request was unsuccessful
//                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
//                            Log.d(TAG, "onResponse: Response Body: " + response.message());
//
//                            // Provide an error message to the user
//                            Toast.makeText(EditProfilePage.this, "Profile update failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<UserResponse> call, Throwable t) {
//                        // Log the failure message
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//
//                        // Provide an error message to the user
//                        Toast.makeText(EditProfilePage.this, "Profile update failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

//    private void loadUserDetails() {
//        // Implement the logic to fetch and display user details here
//        // You should populate the EditText fields with the user's current details
//        // userId can be used to fetch user details from your API or storage
//        // Example:
////        userId = "123"; // Replace with the actual user ID
////        editName.setText("User's Name");
////        editEmail.setText("user@example.com");
////        editPassword.setText("Password");
////        editNic.setText("User's NIC");
////        editPhoneNumber.setText("User's Phone Number");
//        userId = "123"; // Replace with the actual user ID
//        editName.setText("asas");
//        editEmail.setText("user@example.com");
//        editPassword.setText("Password");
//        editNic.setText("User's NIC");
//        editPhoneNumber.setText("User's Phone Number");
//    }
}
