package com.example.ead_frontend_android;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MyBookingFragment extends androidx.fragment.app.Fragment {

    public MyBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_booking, container, false);

        return view;
    }
}