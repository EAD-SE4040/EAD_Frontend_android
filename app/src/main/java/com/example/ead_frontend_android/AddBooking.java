package com.example.ead_frontend_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class AddBooking extends AppCompatActivity {
    private static final String TAG = "AddBooking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Booking");



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("id");
            Log.e(TAG, "onCreate: "+ value );
            //The key argument here must match that used in the other activity
        }


    }
}