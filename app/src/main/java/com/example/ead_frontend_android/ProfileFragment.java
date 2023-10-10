package com.example.ead_frontend_android;



import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ead_frontend_android.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

public class ProfileFragment extends androidx.fragment.app.Fragment {

    private TextView textUsername, textEmail, textPassword, textNIC, textAdditionalInfo;
    private FloatingActionButton editProfileButton;  // Declare as FloatingActionButton
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Access the Toolbar from the parent activity
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);

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
        textAdditionalInfo = view.findViewById(R.id.textAdditionalInfo);
        editProfileButton = view.findViewById(R.id.floatingActionButton);



        // Set the text for the TextViews
        textUsername.setText("Username: Sayanthan");
        textEmail.setText("Email: Sayanthan@gmail.com.com");
        textPassword.setText("Password : ********");
        textNIC.setText("NIC: 993652876V");
        textAdditionalInfo.setText("hi there ");

        // Set an onClickListener for the Edit Profile Button
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfilePage.class);
                startActivity(intent);

                // Define the Intent to navigate to the signup page
//                Intent intent=new Intent(getContext(), EditProfilePage.class);
//                intent.putExtra("id",""+getId());
//                intent.putExtra("userEmail",""+user.getEmail());
//                intent.putExtra("userpasswrod",""+user.getPassword());
//                intent.putExtra("usernic",""+user.getNic());
//                intent.putExtra("userpmail",""+user.getPhone());
//
//                // Start the signup activity
//                startActivity(intent);


                // Handle the click event for the Edit Profile Button
                // You can add your code to open an edit profile activity or fragment here
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu); // Inflate the logout menu
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            // Handle logout button click here
            // You can add code to log the user out, clear session data, etc.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
