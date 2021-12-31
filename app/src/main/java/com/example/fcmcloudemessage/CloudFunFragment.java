package com.example.fcmcloudemessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class CloudFunFragment extends Fragment {

    RecyclerView recycle;
    NotificationAdapter notificationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cloud_fun, container, false);


        recycle=view.findViewById(R.id.recycle);

        recycle.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<NotificationModel> options =
                new FirebaseRecyclerOptions.Builder<NotificationModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notification")
                                .child(FirebaseAuth.getInstance().getUid()).child("Cloudfun") , NotificationModel.class)
                        .build();

        notificationAdapter=new NotificationAdapter(options);
        recycle.setAdapter(notificationAdapter);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        notificationAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        notificationAdapter.stopListening();
    }

}