package com.example.ead_frontend_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.example.ead_frontend_android.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home111:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.booking111:
                    replaceFragment(new MyBookingFragment());
                    break;
                case R.id.profile111:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}




//    private Button button;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        button = findViewById(R.id.callid);
// get trains part

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Call<List<TrainScheduleResponse>> call= RetrofitInstance.getRetrofitClient().create(IService.class).getTrainSchedule();
//
//                call.enqueue(new Callback<List<TrainScheduleResponse>>() {
//                    @Override
//                    public void onResponse(Call<List<TrainScheduleResponse>> call, Response<List<TrainScheduleResponse>> response) {
////                        Log.d(TAG, "onResponse: ");
//                        Log.d(TAG, "onResponse: Response Body: " + response.body().toString());
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<TrainScheduleResponse>> call, Throwable t) {
//                        Log.d(TAG, "onFailure: "+t.fillInStackTrace());
//                    }
//                });
//
//            }
//        });


// add reveration
//
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Reservation reservation=new Reservation();
//
//
//
//                reservation.setUserID("652192d9cb3220501f934583");
//                reservation.setTrainID("6522797b973c66d859a8fc0f");
//                reservation.setReservationDate("2023-11-15T12:00:00");
//                reservation.setNoOfSeates(69);
//                reservation.setStatus(true);
//
//
//
//
//                Call<ReservationResponse> call=RetrofitInstance.getRetrofitClient().create(IService.class).addReservation(reservation);
//
//
//                call.enqueue(new Callback<ReservationResponse>() {
//                    @Override
//                    public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
//                        if (response.isSuccessful()) {
//                            ReservationResponse reservationResponse = response.body();
//                            if (reservationResponse != null) {
//                                // Log the HTTP status code and the response body
//                                Log.d(TAG, "onResponse: HTTP Status Code: " + response.code());
//                                Log.d(TAG, "onResponse: Response Body: " + reservationResponse.toString());
//                            }
//                        } else {
//                            // Log the unsuccessful response
//                            Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
//                            Log.d(TAG, "onResponse: Response Body: " + response.toString());
//                        }
//
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ReservationResponse> call, Throwable t) {
//                        // Log the failure message
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//                    }
//                }
//
//                );
//
//            }
//        });

// edit reveration

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String idd = "65239f97955c2aaf630b6b3b";
//                Reservation reservation=new Reservation();
//
//
//
//                reservation.setUserID("652192d9cb3220501f934583");
//                reservation.setTrainID("6522797b973c66d859a8fc0f");
//                reservation.setReservationDate("2023-11-15T12:00:00");
//                reservation.setNoOfSeates(69);
//                reservation.setStatus(true);
//
//
//
//
//
//                Call<ReservationResponse> call=RetrofitInstance.getRetrofitClient().create(IService.class).EditReservation(idd,reservation);
//
//
//                call.enqueue(new Callback<ReservationResponse>() {
//                                 @Override
//                                 public void onResponse(Call<ReservationResponse> call, Response<ReservationResponse> response) {
//                                     if (response.isSuccessful()) {
//                                         ReservationResponse reservationResponse = response.body();
//                                         if (reservationResponse != null) {
//                                             // Log the HTTP status code and the response body
//                                             Log.d(TAG, "onResponse: HTTP Status Code: " + response.code());
//                                             Log.d(TAG, "onResponse: Response Body: " + reservationResponse.toString());
//                                         }
//                                     } else {
//                                         // Log the unsuccessful response
//                                         Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
//                                         Log.d(TAG, "onResponse: Response Body: " + response.toString());
//                                     }
//
//
//
//                                 }
//
//                                 @Override
//                                 public void onFailure(Call<ReservationResponse> call, Throwable t) {
//                                     // Log the failure message
//                                     Log.d(TAG, "onFailure: " + t.getMessage());
//                                 }
//                             }
//
//                );
//
//            }
//        });

// cacel reveration
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String idd = "65239f97955c2aaf630b6b3b";
//
//                Call<Void> call=RetrofitInstance.getRetrofitClient().create(IService.class).cancelReservation(idd);
//
//
//                call.enqueue(new Callback<Void>() {
//                    @Override
//                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        if (response.isSuccessful()) {
//                            // Handle a successful response
//                            Log.d(TAG, "Reservation canceled successfully");
//                        } else {
//                            // Handle an unsuccessful response (e.g., HTTP error)
//                            Log.d(TAG, "Request failed with HTTP error code: " + response.code());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Void> call, Throwable t) {
//                        // Log the failure message
//                        Log.d(TAG, "onFailure: " + t.getMessage());
//                    }
//                });



//
//            }
//        });


//    }

///}
