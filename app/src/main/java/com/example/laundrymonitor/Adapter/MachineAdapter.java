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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrymonitor.Activity.SelectMachine;
import com.example.laundrymonitor.Model.Machine;
import com.example.laundrymonitor.Model.User;
import com.example.laundrymonitor.R;
import com.example.laundrymonitor.ReminderBroadcast;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.List;

public class MachineAdapter extends RecyclerView.Adapter<MachineAdapter.MyViewHolder> {

    Context context;
    List<Machine> machines;
    Dialog dialog;
    String myUid;

    public MachineAdapter(Context context, List<Machine> machines) {
        this.context = context;
        this.machines = machines;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public MachineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_machine, parent, false);

        context = parent.getContext();
        return new MachineAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MachineAdapter.MyViewHolder holder, final int position) {

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

                            createNotificationChannel();

                            holder.item.setBackgroundColor(context.getResources().getColor(R.color.colorlGrey));
                            holder.kolejSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                            holder.availableSlot.setText("Full");
                            holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorRed));
                            holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));

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

                    final Button yes = (Button) dialog.findViewById(R.id.yesStop);
                    Button cancel = (Button) dialog.findViewById(R.id.cancelStop);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            holder.item.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                            holder.kolejSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                            holder.availableSlot.setText("Available");
                            holder.availableSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));
                            holder.numberSlot.setTextColor(context.getResources().getColor(R.color.colorBlack));


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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView item;
        TextView availableSlot, numberSlot, kolejSlot;
        ImageView machineView;
        Button select;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item = (CardView) itemView.findViewById(R.id.item_machine);
            machineView = (ImageView) itemView.findViewById(R.id.item_image);
            kolejSlot = (TextView) itemView.findViewById(R.id.item_loc);
            numberSlot = (TextView)itemView.findViewById(R.id.item_number);
            availableSlot = (TextView)itemView.findViewById(R.id.item_slot);
            select = (Button) itemView.findViewById(R.id.btnSelect);

        }
    }
}

