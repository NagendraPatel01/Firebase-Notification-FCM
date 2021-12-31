package com.example.fcmcloudemessage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NotificationAdapter extends FirebaseRecyclerAdapter<NotificationModel,NotificationAdapter.ViewHolder> {


    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<NotificationModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull NotificationModel model) {

        holder.text1.setText(model.getTitle());
        holder.text2.setText(model.getDescription());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.notificationlay,parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1,text2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
        }
    }

}
