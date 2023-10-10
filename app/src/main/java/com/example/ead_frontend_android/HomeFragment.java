package com.example.ead_frontend_android;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.adapter.IBooking;
import com.example.ead_frontend_android.adapter.TrainAdapter;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.SharedPreferences;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends androidx.fragment.app.Fragment implements IBooking {
    private static final String TAG = "HomeFragment";
    
    private RecyclerView recyclerView;
    private TrainAdapter adapter;

    private Button button;

    private List<TrainScheduleResponse> trainSchedules;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        // Set the Toolbar as the ActionBar for this fragment's activity
        if (getActivity() instanceof AppCompatActivity) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        }
        setHasOptionsMenu(true);


        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_home, container, false);
       
       recyclerView=view.findViewById(R.id.recycleid);
       recyclerView.setHasFixedSize(true);
       callAPI();


       


       
       
       return view;
    }

    private void callAPI() {

        Call<List<TrainScheduleResponse>> call = RetrofitInstance.getRetrofitClient().create(IService.class).getTrainSchedule();

        call.enqueue(new Callback<List<TrainScheduleResponse>>() {
            @Override
            public void onResponse(Call<List<TrainScheduleResponse>> call, Response<List<TrainScheduleResponse>> response) {
                Log.d(TAG, "onResponse: Response Body: " + response.body().toString());
                trainSchedules = response.body();
                adapter=new TrainAdapter(getContext(),response.body());
                recyclerView.setAdapter(adapter);
                adapter.setiBooking(HomeFragment.this::booking);

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onFailure(Call<List<TrainScheduleResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.fillInStackTrace());

            }
        });

    }


    @Override
    public void booking(int position) {
        Log.e(TAG, "booking: "+ position );
        Intent intent=new Intent(getContext(), AddBooking.class);
        TrainScheduleResponse selected=trainSchedules.get(position);
        intent.putExtra("id",""+selected.getId());
        intent.putExtra("trainname",""+selected.getTrainName());
        intent.putExtra("sheduledatetime",""+selected.getScheduleDateTime());




        startActivity(intent);
//        intent.putExtra("id",selected.getId());
//        intent.putExtra("id",selected.getId());

    }
}