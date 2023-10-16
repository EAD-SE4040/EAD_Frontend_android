package com.example.ead_frontend_android;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditReservation extends AppCompatActivity {
    private static final String TAG = "EditReservation";

    private EditText seatCountInput, nicInput;

    private Button editbooking;


    private TextView schedule_date_time_text, trainname;

    String trainid;

    String reservationid;

    String sheduledatetime;
    String userId;
    String trainName;
    String status;
    String Nicnumber;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_booking);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarupdate);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Booking");


        schedule_date_time_text = findViewById(R.id.reservation_date_text);
        seatCountInput = findViewById(R.id.editable_seats_count);
        nicInput = findViewById(R.id.Nicedit);
        editbooking = findViewById(R.id.save_button);
        trainname = findViewById(R.id.trainName);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {


            reservationid = extras.getString("id");
            trainid = extras.getString("trainid");
            sheduledatetime = extras.getString("sheduledatetime");
            userId = extras.getString("userid");
            trainName = extras.getString("Trainname");
            status = extras.getString("status");
            Nicnumber = extras.getString("Nic");

//            Log.d(TAG, "onResponse: Reservation added: " + userId.toString());
//            Log.d(TAG, "onResponse: Reservation added: " + trainid.toString());
//            Log.d(TAG, "onResponse: Reservation added: " + reservationid.toString());
//            Log.d(TAG, "onResponse: Reservation added: " + sheduledatetime.toString());
//            Log.d(TAG, "onResponse: Reservation added: " + trainName.toString());

            // Assuming sheduledatetime is in the format "yyyy-MM-dd HH:mm:ss"
//            String sheduledatetime = extras.getString("sheduledatetime");
//            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date;
//
//            try {
//                date = inputFormat.parse(sheduledatetime);
//
//                // Now, you can format the date into a different format, e.g., "MMM dd, yyyy hh:mm a"
//                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
//                String formattedDateTime = outputFormat.format(date);
//                schedule_date_time_text.setText("Scheduled Time : " + formattedDateTime);
//                // Now, formattedDateTime contains the formatted date and time
//                // You can use it as needed
//                Log.d("FormattedDateTime", formattedDateTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }


            schedule_date_time_text.setText("Scheduled Time : " + sheduledatetime);
            trainname.setText("Train Name : " + trainName);
            nicInput.setText(Nicnumber);


        }

        editbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the user-entered seat count from the seatCountInput EditText
                String seatCountStr = seatCountInput.getText().toString();
                String Nicnumber = nicInput.getText().toString();

                // Validate the input (you can add more validation logic)
                if (seatCountStr.isEmpty()) {


                    Toast.makeText(EditReservation.this, "Need to Required the SeatCount", Toast.LENGTH_SHORT).show();

                    return;
                }


                if (Nicnumber.isEmpty()) {


                    Toast.makeText(EditReservation.this, "Need to Required the NIC", Toast.LENGTH_SHORT).show();

                    return;
                }

                // Parse the seat count as an integer
                int seatCount = Integer.parseInt(seatCountStr);


                // Create a Reservation object with the user input
                Reservation reservation = new Reservation();
                reservation.setId(reservationid);
                reservation.setUserID(userId);
                reservation.setTrainID(trainid);
                reservation.setReservationDate(sheduledatetime);
                reservation.setNoOfSeates(seatCount);
                reservation.setStatus(true);
                reservation.setNic(Nicnumber);
                reservation.setTrainName(trainName);


                // Create a Retrofit call to add the reservation
                Call<ReservationResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).EditReservation(reservationid, reservation);


                // Enqueue the call to send the reservation request
                call.enqueue(new Callback<ReservationResponse>() {
                    @Override
                    public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
                        if (response.isSuccessful()) {
//                            if (response.code() == 204) {
                            Log.d(TAG, "onResponse: 204 Request " + response.code());

                            // Successful request with no content in the response body
                            Toast.makeText(EditReservation.this, "Booking Edited successfully ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditReservation.this, MainActivity.class);

                            startActivity(intent);
                            finish();


//                            }
                        } else if (response.code() == 400) {
                            Log.d(TAG, "onResponse: Bad Request (400)");

                            try {
                                // Extract the error message from the response body
                                String errorBody = response.errorBody().string();


                                Toast.makeText(EditReservation.this, errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                // Handle any error while extracting the error message
                                Toast.makeText(EditReservation.this, "Error processing the response", Toast.LENGTH_SHORT).show();
                            }
                        } else if (response.code() == 404) {
                            try {
                                // Extract the error message from the response body
                                String errorBody = response.errorBody().string();


                                // You can then use the error message to display it to the user
                                Toast.makeText(EditReservation.this, errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                // Handle any error while extracting the error message
                                Toast.makeText(EditReservation.this, "Error processing the response", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Log.d(TAG, "onResponse: HTTP Status Code: " + response.code());
                            Log.d(TAG, "onResponse: Response Body: " + response.toString());


                            // You can provide an error message to the user here
                            Toast.makeText(EditReservation.this, "Edit Booking failed 1", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ReservationResponse> call, Throwable t) {
                        // Log the failure message
                        Log.d(TAG, "onFailure: " + t.getMessage());

                        // You can provide an error message to the user here
                        Toast.makeText(EditReservation.this, "Edit Booking failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        editbooking.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "Button Clicked");
//                // Get the user-entered seat count from the seatCountInput EditText
//                String seatCountStr = seatCountInput.getText().toString();
//                String Nicnumber = nicInput.getText().toString();
//
//                // Validate the input (you can add more validation logic)
//                if (seatCountStr.isEmpty() && Nicnumber.isEmpty()) {
////                    LayoutInflater inflater = getLayoutInflater();
////                    View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_layout));
//
////                    TextView text = layout.findViewById(R.id.custom_toast_text);
//
//                    Toast.makeText(EditReservation.this, "Please filled all fileds ", Toast.LENGTH_SHORT).show();
//
//                    return;
//                }
//
//                // Parse the seat count as an integer
//                int seatCount = Integer.parseInt(seatCountStr);
//
//
//                // Create a Reservation object with the user input
//                Reservation reservation = new Reservation();
//                reservation.setId(reservationid);
//                reservation.setUserID(userId);
//                reservation.setTrainID(trainid);
//                reservation.setReservationDate(sheduledatetime);
//                reservation.setNoOfSeates(seatCount);
//                reservation.setStatus(true);
//                reservation.setNic(Nicnumber);
//                reservation.setTrainName(trainName);
//
//                Log.d(TAG, "Button Clicked 2");
//                // Create a Retrofit call to add the reservation
//                Call<ReservationResponse> call = RetrofitInstance.getRetrofitClient().create(IService.class).EditReservation(reservationid, reservation);
//                Log.d(TAG, "Button Clicked 3 ");
//                // Enqueue the call to send the reservation request
//                call.enqueue(new Callback<ReservationResponse>() {
//                    @Override
//                    public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
//                        if (response.isSuccessful()) {
//                            ReservationResponse reservationResponse = response.body();
//                            Log.d(TAG, "Button Clicked 4" +reservationResponse );
//                            if (reservationResponse != null) {
//                                // Reservation was successfully added
//                                Log.d(TAG, "onResponse: Reservation added: " + reservationResponse.toString());
//
//                                // You can provide feedback to the user here (e.g., a success message)
//                                Toast.makeText(EditReservation.this, "Edit Booking successful", Toast.LENGTH_SHORT).show();
//
//                                // Navigate to MainActivity after successful reservation
//                                Intent intent = new Intent(EditReservation.this, MyBookingFragment.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        } else if (response.code() == 400) {
//                            // Handle the 400 Bad Request error here
//                            Log.d(TAG, "onResponse: Bad Request (400)");
//
//                            try {
//                                // Extract the error message from the response body
//                                String errorBody = response.errorBody().string();
//                                // Parse the errorBody to get the error message
//                                // The structure of the error message may depend on how the server is set up.
//                                // You may need to parse the JSON response or handle it according to your server's response format.
//                                // For example, you can use a JSON parser like Gson to parse the error message if it's in JSON format.
//
//                                // You can then use the error message to display it to the user
//                                Toast.makeText(EditReservation.this, errorBody, Toast.LENGTH_SHORT).show();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                                // Handle any error while extracting the error message
//                                Toast.makeText(EditReservation.this, "Error processing the response", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            // Handle other error cases (e.g., 401 Unauthorized, 404 Not Found, etc.) here
//                            Log.d(TAG, "onResponse: HTTP Status Code: " + response.code());
//                            Log.d(TAG, "onResponse: Response Body: " + response.toString());
//
//
//
//                            // You can provide an error message to the user here
//                            Toast.makeText(EditReservation.this, "Edit Booking failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ReservationResponse> call, Throwable t) {
//                        // Log the failure message
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//
//                        // You can provide an error message to the user here
//                        Toast.makeText(EditReservation.this, "Edit Booking failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//
//        });
//
//
//    }
    }
}
