package com.example.ead_frontend_android;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.adapter.BookingAdapter;
import com.example.ead_frontend_android.adapter.IBooking;
import com.example.ead_frontend_android.adapter.IResevation;
import com.example.ead_frontend_android.adapter.TrainAdapter;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MyBookingFragment extends androidx.fragment.app.Fragment implements IResevation {
    private static final String TAG = "MyBookingFragment";
    private RecyclerView recyclerView;
    private BookingAdapter adapter;

    private Button editbutton;
    private Button cancelbutton;

    String userId;

    private List<ReservationResponse> reservationResponses;

    public MyBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences preferences = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        userId = preferences.getString("userId", null);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_booking, container, false);
        recyclerView = view.findViewById(R.id.bookingrecycleid);

        recyclerView.setHasFixedSize(true);

        ProgressBar bookingProgressBar = view.findViewById(R.id.bookingProgressBar);
        TextView emptyBookingTextView = view.findViewById(R.id.emptyBookingTextView);

        // Initially, show loading indicator and hide RecyclerView
        bookingProgressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyBookingTextView.setVisibility(View.GONE);

        callAPI(bookingProgressBar, recyclerView, emptyBookingTextView);

        return view;
    }


    private void callAPI(ProgressBar bookingProgressBar, RecyclerView recyclerView, TextView emptyBookingTextView) {
        Call<List<ReservationResponse>> call = RetrofitInstance.getRetrofitClient().create(IService.class).getUserReservation(userId);


        call.enqueue(new Callback<List<ReservationResponse>>() {
            @Override
            public void onResponse(Call<List<ReservationResponse>> call, Response<List<ReservationResponse>> response) {
                bookingProgressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    reservationResponses = response.body();
                    if (reservationResponses != null && !reservationResponses.isEmpty()) {
                        adapter = new BookingAdapter(getContext(), response.body());
                        recyclerView.setAdapter(adapter);
                        adapter.setiResevation(MyBookingFragment.this::resevation);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        emptyBookingTextView.setVisibility(View.VISIBLE);
                    }
                } else {
                    Log.d(TAG, "onResponse: Unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<ReservationResponse>> call, Throwable t) {
                bookingProgressBar.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t.fillInStackTrace());
            }
        });




    }








    @Override
    public void resevation (int position) {
        Log.e(TAG, "booking: "+ position );
        Intent intent=new Intent(getContext(), EditReservation.class);
        ReservationResponse selected=reservationResponses.get(position);
        intent.putExtra("id",""+selected.getId());
        intent.putExtra("trainid",""+selected.getTrainID());
        intent.putExtra("sheduledatetime",""+selected.getReservationDate());
        intent.putExtra("userid",""+selected.getUserID());
        String idd = selected.getId();



        startActivity(intent);



    }




}
