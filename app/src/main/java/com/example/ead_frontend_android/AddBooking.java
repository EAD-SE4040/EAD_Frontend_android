package com.example.ead_frontend_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;
import com.example.ead_frontend_android.model.Reservation;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBooking extends AppCompatActivity {
    private static final String TAG = "AddBooking";
    String trainid;
    String trainName;
    String sheduledatetime;
    String userId;

    String NIC ;


    private EditText seatCountInput;

    private Button booking ;

    private TextView train_name_text , schedule_date_time_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
         userId = preferences.getString("userId", null);

        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Booking");

        train_name_text = findViewById(R.id.train_name_text);
        schedule_date_time_text = findViewById(R.id.schedule_date_time_text);
        seatCountInput = findViewById(R.id.seat_count_input);
        booking = findViewById(R.id.book_now_button);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            trainid = extras.getString("id");
            trainName = extras.getString("trainname");
            sheduledatetime = extras.getString("sheduledatetime");

            train_name_text.setText(trainName);
            schedule_date_time_text.setText("Scheduled Time: " + sheduledatetime);
//            seatCountInput.setText(id);

            Log.e(TAG, "onCreate: trainName: " + trainName);
        }



//        booking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the user-entered seat count from the seatCountInput EditText
//                String seatCountStr = seatCountInput.getText().toString();
//
//                // Validate the input (you can add more validation logic)
//                if (seatCountStr.isEmpty()) {
//                    LayoutInflater inflater = getLayoutInflater();
//                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
//
//                    TextView text = layout.findViewById(R.id.custom_toast_text);
//                    // Show an error message to the user (e.g., by displaying a Toast)
//                    Toast.makeText(AddBooking.this, "Please enter the seat count", Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
//
//                // Parse the seat count as an integer
//                int seatCount = Integer.parseInt(seatCountStr);
//                NIC =  "992591641V";
//
//                // Create a Reservation object with the user input
//                Reservation reservation = new Reservation();
//                reservation.setUserID(userId);
//                reservation.setTrainID(trainid);
//                reservation.setReservationDate(sheduledatetime);
//                reservation.setNoOfSeates(seatCount); // Set the user-entered seat count
//                reservation.setStatus(true);
//
//                // Create a Retrofit call to add the reservation
//                Call<ReservationResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).addReservation(reservation);
//
//                // Enqueue the call to send the reservation request
//                call.enqueue(new Callback<ReservationResponse>() {
//                    @Override
//                    public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
//                        if (response.isSuccessful()) {
//                            ReservationResponse reservationResponse = response.body();
//                            if (reservationResponse != null) {
//                                // Reservation was successfully added
//                                Log.d(TAG, "onResponse: Reservation added: " + reservationResponse.toString());
//
//                                // You can provide feedback to the user here (e.g., a success message)
//                                Toast.makeText(AddBooking.this, "Booking successful", Toast.LENGTH_SHORT).show();
//
//                                // Navigate to MainActivity after successful reservation
//                                Intent intent = new Intent(AddBooking.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        } else {
//                            // Reservation request was unsuccessful
//                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
//                            Log.d(TAG, "onResponse: Response Body: " + response.toString());
//
//                            // You can provide an error message to the user here
//                            Toast.makeText(AddBooking.this, "Booking failed", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ReservationResponse> call, Throwable t) {
//                        // Log the failure message
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//
//                        // You can provide an error message to the user here
//                        Toast.makeText(AddBooking.this, "Booking failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });


        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current date
                Calendar currentDate = Calendar.getInstance();

                // Calculate the maximum allowed date (30 days from the current date)
                Calendar maxAllowedDate = Calendar.getInstance();
                maxAllowedDate.add(Calendar.DAY_OF_MONTH, 30);
                Log.d(TAG, "onResponse: 1");

                // Parse the reservation date as a Date object (modify this based on your data structure)
                Date reservationDate = parseReservationDate(sheduledatetime);
                Log.d(TAG, "onResponse: 2");

                // Check if the traveler already has 4 or more reservations
                if (numberOfReservations() >= 4) {
                    showSnackbarError("You already have 4 or more reservations. You cannot make additional reservations.");
                } else if (reservationDate != null) {
                    if (reservationDate.after(currentDate.getTime()) && reservationDate.before(maxAllowedDate.getTime())) {
                        // Reservation date is within the allowed range
                        // Proceed with making the reservation
                        Log.d(TAG, "onResponse: 3");
                                        String seatCountStr = seatCountInput.getText().toString();

                // Validate the input (you can add more validation logic)
                if (seatCountStr.isEmpty()) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));

                    TextView text = layout.findViewById(R.id.custom_toast_text);
                    // Show an error message to the user (e.g., by displaying a Toast)
                    Toast.makeText(AddBooking.this, "Please enter the seat count", Toast.LENGTH_SHORT).show();

                    return;
                }

                // Parse the seat count as an integer
                int seatCount = Integer.parseInt(seatCountStr);
                NIC =  "992591641V";
                Log.d(TAG, "onResponse: 4");
                // Create a Reservation object with the user input
                Reservation reservation = new Reservation();
                reservation.setUserID(userId);
                reservation.setTrainID(trainid);
                reservation.setReservationDate(sheduledatetime);
                reservation.setNoOfSeates(seatCount); // Set the user-entered seat count
                reservation.setStatus(true);

                // Create a Retrofit call to add the reservation
                Call<ReservationResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).addReservation(reservation);
                        call.enqueue(new Callback<ReservationResponse>() {
                            @Override
                            public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                                if (response.isSuccessful()) {
                                    ReservationResponse reservationResponse = response.body();
                                    if (reservationResponse != null) {
                                        // Reservation was successfully added
                                        Log.d(TAG, "onResponse: Reservation added: " + reservationResponse.toString());
                                        Toast.makeText(AddBooking.this, "Booking successful", Toast.LENGTH_SHORT).show();

                                        // Navigate to MainActivity after successful reservation
                                        Intent intent = new Intent(AddBooking.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    // Handle error responses as previously explained
                                    String errorResponse = response.errorBody().toString();
                                    showSnackbarError(errorResponse);
                                }
                            }

                            @Override
                            public void onFailure(Call<ReservationResponse> call, Throwable t) {
                                // Handle network errors as previously explained
                                Log.d(TAG, "onFailure: " + t.getMessage());
                                showSnackbarError("Booking failed. Network error occurred.");
                            }
                        });
                    } else {
                        // Reservation date is outside the allowed range
                        showSnackbarError("Reservation date must be within 30 days from the booking date.");
                    }
                } else {
                    // Invalid reservation date
                    showSnackbarError("Invalid reservation date.");
                }
            }
        });




    }
    // Function to parse the reservation date (modify this based on your data structure)
//    private Date parseReservationDate(String dateStr) {
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
//            return dateFormat.parse(dateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    private Date parseReservationDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            // Show an error message to the user
            Toast.makeText(AddBooking.this, "Invalid date format", Toast.LENGTH_SHORT).show();
            return null;
        }
    }



    // Function to get the number of traveler's existing reservations
    private int numberOfReservations() {
        // Implement logic to fetch and count the traveler's existing reservations from your data source
        // Return the count of reservations

        // For example, you might use another Retrofit call to get the traveler's reservations and count them
        // Note: This code is a simplified example and may not work directly with your specific backend API or database
        int count = 0;
        Call<List<ReservationResponse>> getReservationsCall = RetrofitInstance.getRetrofitClient().create(IService.class).getUserReservation(userId);
        try {
            Response<List<ReservationResponse>> response = getReservationsCall.execute();
            if (response.isSuccessful() && response.body() != null) {
                count = response.body().size();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
    private void showSnackbarError(String errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_SHORT).show();
    }

}