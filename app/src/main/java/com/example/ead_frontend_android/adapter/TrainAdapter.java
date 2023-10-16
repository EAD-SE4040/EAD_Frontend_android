package com.example.ead_frontend_android.adapter;

import static androidx.core.content.ContextCompat.startActivity;
import static java.lang.String.format;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_frontend_android.AddBooking;
import com.example.ead_frontend_android.HomeFragment;
import com.example.ead_frontend_android.Login;
import com.example.ead_frontend_android.R;
import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.SignUp;
import com.example.ead_frontend_android.model.TrainSchedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.TrainViewholder> {
    private Context context;
    private List<TrainScheduleResponse> mlist;

    private IBooking iBooking;

    public void setiBooking(IBooking iBooking) {
        this.iBooking = iBooking;
    }

    public TrainAdapter(Context context, List<TrainScheduleResponse> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public TrainViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.train_schedule_item,parent,false);
        return new TrainViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewholder holder, int position) {


        TrainScheduleResponse trainSchedule=mlist.get(position);
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date;

//
//        try {
//            date = inputFormat.parse(trainSchedule.getScheduleDateTime());
//
//            // Now, you can format the date into a different format, e.g., "MMM dd, yyyy hh:mm a"
//            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
//            String formattedDateTime = outputFormat.format(date);
//            holder.scheduleTime.setText("Scheduled Time - "+ formattedDateTime);
//            // Now, formattedDateTime contains the formatted date and time
//            // You can use it as needed
//            Log.d("FormattedDateTime", formattedDateTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }



        holder.tvTitle.setText(trainSchedule.getTrainName());
        holder.scheduleTime.setText(trainSchedule.getScheduleDateTime());
        holder.seat.setText("Available seats -  "+trainSchedule.getSeatsCount());
        holder.formtext.setText("Departure - "+trainSchedule.getFrom());
        holder.totext.setText("Destination -  "+trainSchedule.getTo());


    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    public class TrainViewholder extends RecyclerView.ViewHolder {
        private TextView tvTitle,scheduleTime,seat,formtext,totext;
        private Button book_button;
        public TrainViewholder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.train_name_text);
            scheduleTime=itemView.findViewById(R.id.schedule_date_time_text);
            seat=itemView.findViewById(R.id.seats_count_text);
            formtext =itemView.findViewById(R.id.from_text);
            totext =itemView.findViewById(R.id.to_text);
            book_button =itemView.findViewById(R.id.book_now_button);



            book_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(iBooking!=null){
                        int postion=getAdapterPosition();
                        if(postion!=RecyclerView.NO_POSITION){
                            iBooking.booking(postion);
                        }
                    }
                }
            });

        }
    }
}
