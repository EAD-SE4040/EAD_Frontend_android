package com.example.ead_frontend_android.adapter;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.ead_frontend_android.MyBookingFragment;
import com.example.ead_frontend_android.EditReservation;
import com.example.ead_frontend_android.R;
import com.example.ead_frontend_android.Response.ReservationResponse;
import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.api.IService;
import com.example.ead_frontend_android.api.RetrofitInstance;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date;
//
//        try {
//            date = inputFormat.parse(reservationResponse.getReservationDate());
//
//            // Now, you can format the date into a different format, e.g., "MMM dd, yyyy hh:mm a"
//            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
//            String formattedDateTime = outputFormat.format(date);
//            holder.reservation_date_text.setText("scheduled Date & Time : "+ formattedDateTime);
//            // Now, formattedDateTime contains the formatted date and time
//            // You can use it as needed
//            Log.d("FormattedDateTime", formattedDateTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        holder.trainName.setText(reservationResponse.getTrainName());
        holder.reservation_date_text.setText("scheduled Date & Time : "+ reservationResponse.getReservationDate());
        holder.seats_count_text.setText("Booked Seats : " + reservationResponse.getNoOfSeates());
        holder.Nic.setText("NIC  Number : " + reservationResponse.getNic());





    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class BookinViewholder extends RecyclerView.ViewHolder{
        private TextView reservation_date_text,seats_count_text,status_text,Nic,trainName;
        private Button edit_button,cancel_button;
        public BookinViewholder(@NonNull View itemView) {
            super(itemView);

            reservation_date_text=itemView.findViewById(R.id.reservation_date_text);
            Nic=itemView.findViewById(R.id.Nic);
            seats_count_text=itemView.findViewById(R.id.seats_count_text);
            edit_button = itemView.findViewById(R.id.edit_button); // Initialize the edit_button
            cancel_button = itemView.findViewById(R.id.cancel_button); // Initialize the cancel_button
            trainName = itemView.findViewById(R.id.reservation_TrainName); // Initialize the cancel_button

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Confirm Cancellation");
                    builder.setMessage("Are you sure you want to cancel this reservation?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
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
                                            // Update your data source (mlist) by removing the canceled reservation
                                            // Update your data source (mlist) by removing the canceled reservation
                                            int position = getAdapterPosition();
                                            if (position != RecyclerView.NO_POSITION) {
                                                mlist.remove(position);
                                                notifyDataSetChanged();
                                            }

                                            // You can provide feedback to the user here (e.g., a success message)
                                            Toast.makeText(context, "Reservation canceled", Toast.LENGTH_SHORT).show();

                                            // Optionally, you can navigate back to the previous screen or perform any other action
                                        } else if (response.code() == 400) {
                                            // Handle the 400 Bad Request error here
                                            Log.d(TAG, "onResponse: Bad Request (400)");

                                            try {
                                                // Extract the error message from the response body
                                                String errorBody = response.errorBody().string();
                                                // Parse the errorBody to get the error message
                                                // The structure of the error message may depend on how the server is set up.
                                                // You may need to parse the JSON response or handle it according to your server's response format.
                                                // For example, you can use a JSON parser like Gson to parse the error message if it's in JSON format.

                                                // You can then use the error message to display it to the user
                                                Toast.makeText(context, errorBody, Toast.LENGTH_SHORT).show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                                // Handle any error while extracting the error message
                                                Toast.makeText(context, "Error processing the response", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else{
                                            // Handle other error cases (e.g., 401 Unauthorized, 404 Not Found, etc.) here
                                            Log.d(TAG, "onResponse: HTTP Status Code: " + response.code());
                                            Log.d(TAG, "onResponse: Response Body: " + response.toString());

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
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            });

//            cancel_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        ReservationResponse selected = mlist.get(position);
//
//                        // Make the DELETE request to cancel the reservation
//                        Call<Void> call = RetrofitInstance.getRetrofitClient().create(IService.class).cancelReservation(selected.getId());
//
//                        // Enqueue the call to send the request
//                        call.enqueue(new Callback<Void>() {
//                            @Override
//                            public void onResponse(Call<Void> call, Response<Void> response) {
//                                if (response.isSuccessful()) {
//                                    // Reservation was successfully canceled
//                                    Log.d(TAG, "onResponse: Reservation canceled successfully");
//
//                                    // You can provide feedback to the user here (e.g., a success message)
//                                    Toast.makeText(context, "Reservation canceled", Toast.LENGTH_SHORT).show();
//
//                                    // Optionally, you can navigate back to the previous screen or perform any other action
//                                } else if (response.code() == 400) {
//                                    // Handle the 400 Bad Request error here
//                                    Log.d(TAG, "onResponse: Bad Request (400)");
//
//                                    try {
//                                        // Extract the error message from the response body
//                                        String errorBody = response.errorBody().string();
//                                        // Parse the errorBody to get the error message
//                                        // The structure of the error message may depend on how the server is set up.
//                                        // You may need to parse the JSON response or handle it according to your server's response format.
//                                        // For example, you can use a JSON parser like Gson to parse the error message if it's in JSON format.
//
//                                        // You can then use the error message to display it to the user
//                                        Toast.makeText(context, errorBody, Toast.LENGTH_SHORT).show();
//                                    } catch (IOException e) {
//                                        e.printStackTrace();
//                                        // Handle any error while extracting the error message
//                                        Toast.makeText(context, "Error processing the response", Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    // Handle other error cases (e.g., 401 Unauthorized, 404 Not Found, etc.) here
//                                    Log.d(TAG, "onResponse: HTTP Status Code: " + response.code());
//                                    Log.d(TAG, "onResponse: Response Body: " + response.toString());
//
//                                    // You can provide an error message to the user here
//                                    Toast.makeText(context, "Booking failed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<Void> call, Throwable t) {
//                                // Log the failure message
//                                Log.d(TAG, "onFailure: " + t.getMessage());
//
//                                // You can provide an error message to the user here
//                                Toast.makeText(context, "Cancellation failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//            });




        }

        }
    }


