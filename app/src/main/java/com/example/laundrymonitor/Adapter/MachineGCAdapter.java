package com.example.laundrymonitor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrymonitor.R;
import com.example.laundrymonitor.TimeSlot;

import java.util.List;

public class MachineGCAdapter extends RecyclerView.Adapter<MachineGCAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;

    public MachineGCAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_machine_gc, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (timeSlotList.size() == 0){

            holder.numberSlot.setText("MACHINE");
            holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
            holder.availableSlot.setText("AVAILABLE");
            holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));

        }
        else {

            for (TimeSlot slotValue:timeSlotList) {

                int slot = Integer.parseInt(slotValue.getSlot().toString());
                if (slot == position){

                    holder.machineSlot.setCardBackgroundColor(context.getResources().getColor(R.color.colorRed));

                    holder.availableSlot.setText("FULL");
                    holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorWhite));

                }

            }
        }
    }

    @Override
    public int getItemCount() {

        return timeSlotList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView availableSlot, numberSlot;
        CardView machineSlot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            machineSlot = (CardView)itemView.findViewById(R.id.machineGC);

            availableSlot = (TextView)itemView.findViewById(R.id.slotGC);
            numberSlot = (TextView)itemView.findViewById(R.id.numGC);

        }
    }
}
