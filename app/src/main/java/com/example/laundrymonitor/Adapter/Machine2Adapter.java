package com.example.laundrymonitor.Adapter;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrymonitor.ItemClickListener;
import com.example.laundrymonitor.Model.Common;
import com.example.laundrymonitor.Model.Machine;
import com.example.laundrymonitor.Model.Machine2;
import com.example.laundrymonitor.Model.User;
import com.example.laundrymonitor.R;
import com.example.laundrymonitor.Reminder2Broadcast;
import com.example.laundrymonitor.ReminderBroadcast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Machine2Adapter extends RecyclerView.Adapter<Machine2Adapter.MyViewHolder> {

    Context context;
    List<Machine2> machines;
    Dialog dialog;

    int row_index = -1;

    public Machine2Adapter(Context context, List<Machine2> machines) {
        this.context = context;
        this.machines = machines;
    }

    @NonNull
    @Override
    public Machine2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_machine2, parent, false);

        context = parent.getContext();
        return new Machine2Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Machine2Adapter.MyViewHolder holder, final int position) {

        holder.kolejSlot.setText(machines.get(position).getKolej());
        holder.numberSlot.setText(machines.get(position).getMachineSlot());
        holder.availableSlot.setText(machines.get(position).getSlot());
        holder.machineView.setImageResource(R.drawable.washer);
        holder.select.setBackgroundColor(context.getResources().getColor(R.color.colorTransparent));

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String slot = holder.availableSlot.getText().toString();

                if (slot.equals("Available")){

                    dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_machine_gc);
                    createNotificationChannel();

                    Button yes = (Button) dialog.findViewById(R.id.yesStart);
                    Button cancel = (Button) dialog.findViewById(R.id.cancel);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //updateMachine(position);

                            holder.item.setBackgroundColor(context.getResources().getColor(R.color.colorlGrey));
                            holder.kolejSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                            holder.availableSlot.setText("Full");
                            holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorRed));
                            holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));

                            // notifyDataSetChanged();

                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                            Intent intent = new Intent(context, ReminderBroadcast.class);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                    SystemClock.elapsedRealtime() +
                                            10 * 1000, pendingIntent);

                            Toast.makeText(context,"Machine selected" ,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }
                else if (slot.equals("Full")){

                    dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_machine_end);
                    //cancelNotificationChannel();

                    Button yes = (Button) dialog.findViewById(R.id.yesStop);
                    Button cancel = (Button) dialog.findViewById(R.id.cancelStop);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            //updateMachine(position);

                            holder.item.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                            holder.kolejSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                            holder.availableSlot.setText("Available");
                            holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                            holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));

                            // notifyDataSetChanged();

                            Toast.makeText(context,"Machine stopped" ,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        /*holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String slot = holder.availableSlot.getText().toString();

                if (slot.equals("Available")){

                    dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_machine_gc);

                    Button yes = (Button) dialog.findViewById(R.id.yesStart);
                    Button cancel = (Button) dialog.findViewById(R.id.cancel);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            row_index = position;
                            Common.currentItem = machines.get(position);
                            notifyDataSetChanged();

                            Toast.makeText(context,"Machine selected" ,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                    if (row_index == position){

                        holder.item.setBackgroundColor(context.getResources().getColor(R.color.colorlGrey));
                        holder.kolejSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                        holder.availableSlot.setText("FULL");
                        holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorRed));
                        holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));

                    }

                }
                else if (slot.equals("Full")){

                    dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_machine_end);

                    Button yes = (Button) dialog.findViewById(R.id.yesStop);
                    Button cancel = (Button) dialog.findViewById(R.id.cancelStop);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            row_index = position;
                            Common.currentItem = machines.get(position);
                            notifyDataSetChanged();

                            Toast.makeText(context,"Machine selected" ,Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                    if (row_index == position){

                        holder.item.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                        holder.kolejSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                        holder.availableSlot.setText("Available");
                        holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                        holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));

                    }
                }
            }
        });*/
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ReminderChannel";
            String description = "Channel Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



    @Override
    public int getItemCount() {

        return machines.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView item;
        TextView availableSlot, numberSlot, kolejSlot;
        ImageView machineView;
        Button select;
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item = (CardView) itemView.findViewById(R.id.item_machine2);
            machineView = (ImageView) itemView.findViewById(R.id.item_image2);
            kolejSlot = (TextView) itemView.findViewById(R.id.item_loc2);
            numberSlot = (TextView)itemView.findViewById(R.id.item_number2);
            availableSlot = (TextView)itemView.findViewById(R.id.item_slot2);
            select = (Button) itemView.findViewById(R.id.btnSelect2);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());
        }
    }
}

