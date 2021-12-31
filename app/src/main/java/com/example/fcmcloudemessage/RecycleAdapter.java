package com.example.fcmcloudemessage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RecycleAdapter extends FirebaseRecyclerAdapter<User, RecycleAdapter.ViewHolder> {


    public RecycleAdapter(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull User user) {

     holder.text1.setText(user.getUseremail());
     holder.text2.setText(user.getUsername());
  /*  holder.text1.setText(model.getName());
        Log.d("dfghjk", "dfghjkjhgfdfgh "+model.getName());
    holder.text2.setText(model.getEmail());


*/
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=getRef(position).getKey();

                Intent intent=new Intent(holder.card.getContext(),SendNotificationActivity.class);
                intent.putExtra("id",id);
                holder.card.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.adapterlayout,parent,false);
        return new ViewHolder(view);

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text1,text2;
        //LinearLayout linear;

        CardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text1=itemView.findViewById(R.id.text1);
            text2=itemView.findViewById(R.id.text2);
            card=itemView.findViewById(R.id.card);
        }
    }
}
