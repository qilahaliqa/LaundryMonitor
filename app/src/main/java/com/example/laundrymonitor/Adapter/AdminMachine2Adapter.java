package com.example.laundrymonitor.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laundrymonitor.Model.Machine;
import com.example.laundrymonitor.Model.Machine2;
import com.example.laundrymonitor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class AdminMachine2Adapter extends RecyclerView.Adapter<AdminMachine2Adapter.MyViewHolder> {

    Context context;
    List<Machine2> machines;
    Dialog dialog;
    String myUid;

    public AdminMachine2Adapter(Context context, List<Machine2> machines) {
        this.context = context;
        this.machines = machines;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_item_machine2, parent, false);

        context = parent.getContext();
        return new AdminMachine2Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.kolejSlot.setText(machines.get(position).getKolej());
        holder.numberSlot.setText(machines.get(position).getMachineSlot());
        holder.availableSlot.setText(machines.get(position).getSlot());
        holder.machineView.setImageResource(R.drawable.washer);
        holder.delete.setImageResource(R.drawable.ic_delete);

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.warning_delete);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button cancelDel = (Button) dialog.findViewById(R.id.cancelDelete);
                Button yesDel = (Button) dialog.findViewById(R.id.yesDelete);

                yesDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        deleteMachine(position);
                        // Remove the item on remove/button click
                        machines.remove(position);

                        notifyItemRemoved(position);

                        notifyItemRangeChanged(position,machines.size());

                        // Show the removed item label
                        Toast.makeText(context,"Machine removed" ,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });

                cancelDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

    }

    private void deleteMachine(int position){

        String id = machines.get(position).getId();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Kolej Tun Sri Lanang");
        Query query = databaseReference.orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    ds.getRef().removeValue();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    ds.getRef().updateChildren(hashMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(context,"Error. Please try again." ,Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {

        return machines.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView availableSlot, numberSlot, kolejSlot;
        ImageView machineView;
        ImageButton delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            machineView = (ImageView) itemView.findViewById(R.id.image2);
            kolejSlot = (TextView) itemView.findViewById(R.id.loc2);
            numberSlot = (TextView)itemView.findViewById(R.id.number2);
            availableSlot = (TextView)itemView.findViewById(R.id.slot2);
            delete = (ImageButton) itemView.findViewById(R.id.btnDelete2);

        }
    }
}
