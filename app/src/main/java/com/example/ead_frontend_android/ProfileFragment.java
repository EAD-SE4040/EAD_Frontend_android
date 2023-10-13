package com.example.ead_frontend_android;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.Response.UserResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;
import com.example.ead_frontend_android.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends androidx.fragment.app.Fragment {

    private TextView textUsername, textEmail, textPassword, textNIC, textPhoenumber;
    private FloatingActionButton editProfileButton,logoutButton;  // Declare as FloatingActionButton

    private ProgressBar progressBar;

    private String rname,remail,rpassword,rnic,rphonenumver,ruserType;
    boolean ruserisactive;

    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize the ProgressBar
        progressBar = view.findViewById(R.id.progressBar);

        // Access the Toolbar from the parent activity
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);

        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = preferences.getString("userId", null);

        // Set the Toolbar as the ActionBar for this fragment's activity
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }

        setHasOptionsMenu(true);

        // Initialize TextViews and Button
        textUsername = view.findViewById(R.id.textUsername);
        textEmail = view.findViewById(R.id.textEmail);
        textPassword = view.findViewById(R.id.textPassword);
        textNIC = view.findViewById(R.id.textNIC);
        textPhoenumber = view.findViewById(R.id.textPhoenumber);
        editProfileButton = view.findViewById(R.id.floatingActionButton);
        logoutButton = view.findViewById(R.id.logoutButton); // Change to FloatingActionButton

        // Load user data when the fragment is created
        loadUserData();

        // Set an onClickListener for the Edit Profile Button
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ensure that user data has been loaded before proceeding

                    // Start the EditProfilePage activity with user data
                    Intent intent = new Intent(getContext(), EditProfilePage.class);
                    intent.putExtra("id", userId);
                    intent.putExtra("username",rname);
                    intent.putExtra("userEmail",remail);
                    intent.putExtra("userPassword", rpassword);
                    intent.putExtra("userNIC", rnic);
                    intent.putExtra("userPhone", rphonenumver);
                    intent.putExtra("userType", ruserType);
                    intent.putExtra("userIsactive", ruserisactive);
                    startActivity(intent);

            }
        });

        // Set an onClickListener for the Logout Button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout button click here
                // Clear the SharedPreferences userId
                SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("userId");
                editor.remove("usertype");
                editor.clear();
                editor.apply();

                // Navigate to the login or home page after logout
                Intent intent = new Intent(getContext(), Login.class); // Replace with your login or home activity
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                requireActivity().finish(); // Close the current activity
            }
        });


        return view;
    }

    // Load user data from the server
    private void loadUserData() {
        progressBar.setVisibility(View.VISIBLE);

        // Create a Retrofit call to get the user data
        Call<UserResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).getUser(userId);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar

                if (response.isSuccessful()) {
                    // Handle the user data response here
                    UserResponse userResponse = response.body();
                    if (userResponse != null) {
                        User user = userResponse.getUser();

                        rname = user.getName();
                        remail = user.getEmail();
                        rpassword = user.getPassword();
                        rnic = user.getNic();
                        rphonenumver = user.getPhone();
                        ruserType = user.getUserType();
                        ruserisactive = user.isActive();



                        // Update the UI with user data
                        textUsername.setText("Username: " + user.getName());
                        textEmail.setText("Email: " + user.getEmail());
                        textPassword.setText("Password: " + user.getPassword());
                        textNIC.setText("Nic: " + user.getNic());
                        textPhoenumber.setText("PhoneNumber: " + user.getPhone());
                    }
                } else {
                    // Handle the error case
                    // You can display an error message or handle it as needed
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE); // Hide the ProgressBar
                // Handle the network error here
                // You can display an error message or handle it as needed
            }
        });
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.hasVisibleItems();
//        inflater.inflate(R.menu.toolbar_menu, menu); // Inflate the menu
//        super.onCreateOptionsMenu(menu, inflater);
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        item.setVisible(true);
//        int id = item.getItemId();
//
//        if (id == R.id.action_logout) {
//
//            // Handle logout button click here
//            // Clear the SharedPreferences userId
//            SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.remove("userId");
//            editor.apply();
//
//            // Navigate to the login or home page after logout
//            Intent intent = new Intent(getContext(), ProfileFragment.class); // Replace with your login or home activity
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            getActivity().finish(); // Close the current activity
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
