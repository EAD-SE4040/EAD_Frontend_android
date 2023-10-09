package com.example.ead_frontend_android.adapter;

import static java.lang.String.format;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_frontend_android.R;
import com.example.ead_frontend_android.Response.TrainScheduleResponse;
import com.example.ead_frontend_android.model.TrainSchedule;

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

        holder.tvTitle.setText(trainSchedule.getTrainName());
        holder.scheduleTime.setText((trainSchedule.getScheduleDateTime()));
        holder.seat.setText("Available seats "+trainSchedule.getSeatsCount());
        holder.formtext.setText("From "+trainSchedule.getFrom());
        holder.totext.setText("TO "+trainSchedule.getTo());


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
        public TrainViewholder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.train_name_text);
            scheduleTime=itemView.findViewById(R.id.schedule_date_time_text);
            seat=itemView.findViewById(R.id.seats_count_text);
            formtext =itemView.findViewById(R.id.from_text);
            totext =itemView.findViewById(R.id.to_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
