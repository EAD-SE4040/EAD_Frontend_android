package com.example.ead_frontend_android;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startActivity;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;
import com.example.ead_frontend_android.model.Reservation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReservation extends AppCompatActivity {
    private static final String TAG = "EditReservation";

    private EditText seatCountInput;

    private Button editbooking ;


    private TextView  schedule_date_time_text;

    String trainid;

    String reservationid;

    String sheduledatetime;
    String userId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_booking);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarupdate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Booking");


//        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Add Booking");


        schedule_date_time_text = findViewById(R.id.reservation_date_text);
        seatCountInput = findViewById(R.id.editable_seats_count);
        editbooking = findViewById(R.id.save_button);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {


            reservationid = extras.getString("id");
            trainid = extras.getString("trainid");
            sheduledatetime = extras.getString("sheduledatetime");
            userId = extras.getString("userid");


            schedule_date_time_text.setText("Scheduled Time: " + sheduledatetime);


        }


        editbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the user-entered seat count from the seatCountInput EditText
                String seatCountStr = seatCountInput.getText().toString();

                // Validate the input (you can add more validation logic)
                if (seatCountStr.isEmpty()) {
//                    LayoutInflater inflater = getLayoutInflater();
//                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));

//                    TextView text = layout.findViewById(R.id.custom_toast_text);

                    Toast.makeText(EditReservation.this, "Please enter the seat count", Toast.LENGTH_SHORT).show();

                    return;
                }

                // Parse the seat count as an integer
                int seatCount = Integer.parseInt(seatCountStr);


                // Create a Reservation object with the user input
                Reservation reservation = new Reservation();
                reservation.setUserID(userId);
                reservation.setTrainID(trainid);
                reservation.setReservationDate(sheduledatetime);
                reservation.setNoOfSeates(seatCount); // Set the user-entered seat count
                reservation.setStatus(true);

                // Create a Retrofit call to add the reservation
                Call<ReservationResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).EditReservation(reservationid, reservation);

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
                                Toast.makeText(EditReservation.this, "Booking successful", Toast.LENGTH_SHORT).show();

                                // Navigate to MainActivity after successful reservation
//                                Intent intent = new Intent(EditReservation.this, MyBookingFragment.class);
//                                startActivity(intent);
//                                finish();
                            }
                        } else {
                            // Reservation request was unsuccessful
                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
                            Log.d(TAG, "onResponse: Response Body: " + response.message());

                            // You can provide an error message to the user here
                            Toast.makeText(EditReservation.this, "Booking failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ReservationResponse> call, Throwable t) {
                        // Log the failure message
                        Log.d(TAG, "onFailure: " + t.getMessage());

                        // You can provide an error message to the user here
                        Toast.makeText(EditReservation.this, "Booking failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });


    }
}
