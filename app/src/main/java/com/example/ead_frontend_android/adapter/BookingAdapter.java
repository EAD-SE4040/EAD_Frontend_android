package com.example.ead_frontend_android.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_frontend_android.MyBookingFragment;
import com.example.ead_frontend_android.R;
import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookinViewholder>{
    private Context context;
    private List<ReservationResponse> mlist;
    private IResevation iResevation;


    public BookingAdapter(Context context, List<ReservationResponse> mlist) {
        this.context = context;
        this.mlist = mlist;
        this.iResevation = iResevation;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<ReservationResponse> getMlist() {
        return mlist;
    }

    public void setMlist(List<ReservationResponse> mlist) {
        this.mlist = mlist;
    }

    public IResevation getiResevation() {
        return iResevation;
    }

    public void setiResevation(IResevation iResevation) {
        this.iResevation = iResevation;
    }

    @NonNull
    @Override
    public BookingAdapter.BookinViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.my_booking_item,parent,false);
        return new BookinViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookinViewholder holder, int position) {
        ReservationResponse reservationResponse = mlist.get(position);

        holder.reservation_date_text.setText("scheduled Time "+reservationResponse.getReservationDate());
        holder.seats_count_text.setText("Booked Seats " + reservationResponse.getNoOfSeates());



    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class BookinViewholder extends RecyclerView.ViewHolder{
        private TextView reservation_date_text,seats_count_text,status_text;
        private Button edit_button,cancel_button;
        public BookinViewholder(@NonNull View itemView) {
            super(itemView);

            reservation_date_text=itemView.findViewById(R.id.reservation_date_text);
            seats_count_text=itemView.findViewById(R.id.seats_count_text);
            edit_button = itemView.findViewById(R.id.edit_button); // Initialize the edit_button
            cancel_button = itemView.findViewById(R.id.cancel_button); // Initialize the cancel_button

            edit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iResevation != null) {
                        int postion = getAdapterPosition();
                        if (postion != RecyclerView.NO_POSITION) {
                            iResevation.resevation(postion);
                        }
                    }
                }
            });



            cancel_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ReservationResponse selected = mlist.get(position);

                        // Make the DELETE request to cancel the reservation
                        Call<Void> call = RetrofitInstance.getRetrofitClient().create(IService.class).cancelReservation(selected.getId());

                        // Enqueue the call to send the request
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()) {
                                    // Reservation was successfully canceled
                                    Log.d(TAG, "onResponse: Reservation canceled successfully");

                                    // You can provide feedback to the user here (e.g., a success message)
                                    Toast.makeText(context, "Reservation canceled", Toast.LENGTH_SHORT).show();

                                    // Optionally, you can navigate back to the previous screen or perform any other action
                                } else {
                                    // Cancellation request was unsuccessful
                                    Log.d(TAG, "onResponse: Unsuccessful HTTP Status Code: " + response.code());
                                    Log.d(TAG, "onResponse: Response Body: " + response.message());

                                    // You can provide an error message to the user here
                                    Toast.makeText(context, "Cancellation failed", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                // Log the failure message
                                Log.d(TAG, "onFailure: " + t.getMessage());

                                // You can provide an error message to the user here
                                Toast.makeText(context, "Cancellation failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });




        }

        }
    }


