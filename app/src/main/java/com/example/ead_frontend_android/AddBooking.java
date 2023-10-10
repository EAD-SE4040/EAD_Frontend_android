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
//                // Define the Intent to navigate to the signup page
//                Intent intent = new Intent(AddBooking.this, HomeFragment.class);
//
//                // Start the signup activity
//                startActivity(intent);
//            }
//        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user-entered seat count from the seatCountInput EditText
                String seatCountStr = seatCountInput.getText().toString();

                // Validate the input (you can add more validation logic)
                if (seatCountStr.isEmpty()) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));

                    TextView text = layout.findViewById(R.id.custom_toast_text);
                    // Show an error message to the user (e.g., by displaying a Toast)
                    Toast.makeText(AddBooking.this, "Please enter the seat count", Toast.LENGTH_SHORT).show();
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setDuration(Toast.LENGTH_SHORT);
//                    toast.setView(layout);
//                    toast.setText("Please enter the seat count");
//                    toast.show();
                    return;
                }

                // Parse the seat count as an integer
                int seatCount = Integer.parseInt(seatCountStr);
                NIC =  "992591641V";

                // Create a Reservation object with the user input
                Reservation reservation = new Reservation();
                reservation.setUserID(userId);
                reservation.setTrainID(trainid);
                reservation.setReservationDate(sheduledatetime);
                reservation.setNoOfSeates(seatCount); // Set the user-entered seat count
                reservation.setStatus(true);

                // Create a Retrofit call to add the reservation
                Call<ReservationResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).addReservation(reservation);

                // Enqueue the call to send the reservation request
                call.enqueue(new Callback<ReservationResponse>() {
                    @Override
                    public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                        if (response.isSuccessful()) {
                            ReservationResponse reservationResponse = response.body();
                            if (reservationResponse != null) {
                                // Reservation was successfully added
                                Log.d(TAG, "onResponse: Reservation added: " + reservationResponse.toString());

                                // You can provide feedback to the user here (e.g., a success message)
                                Toast.makeText(AddBooking.this, "Booking successful", Toast.LENGTH_SHORT).show();

                                // Navigate to MainActivity after successful reservation
                                Intent intent = new Intent(AddBooking.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            // Reservation request was unsuccessful
                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
                            Log.d(TAG, "onResponse: Response Body: " + response.toString());

                            // You can provide an error message to the user here
                            Toast.makeText(AddBooking.this, "Booking failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ReservationResponse> call, Throwable t) {
                        // Log the failure message
                        Log.d(TAG, "onFailure: " + t.getMessage());

                        // You can provide an error message to the user here
                        Toast.makeText(AddBooking.this, "Booking failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}