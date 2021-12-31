package com.example.fcmcloudemessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;

public class SendNotificationActivity extends AppCompatActivity {

    EditText edit1,edit2;
    Button send;

    String id;

    DialogPlus dialogPlus;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        send=findViewById(R.id.send);

        reference= FirebaseDatabase.getInstance().getReference().child("Notification");

        id=getIntent().getStringExtra("id");
        Log.d("dfghjhgf", "sdfghfgh"+getIntent().getStringExtra("id"));

        dialogPlus = DialogPlus.newDialog(this)
                .setMargin(50,0,50,0)
                .setContentHolder(new ViewHolder(R.layout.dilogplus))
                .setGravity(Gravity.CENTER)
                .setExpanded(false)
                .create();

        LinearLayout layout=(LinearLayout)dialogPlus.getHolderView();

       TextView rest=layout.findViewById(R.id.restapi);
        TextView cloudfun=layout.findViewById(R.id.cloudfunction);

        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogPlus.dismiss();
                HashMap<String,Object> map=new HashMap<>();

                map.put("title",edit1.getText().toString());
                map.put("description",edit2.getText().toString());
                map.put("id",id);

                reference.child(FirebaseAuth.getInstance().getUid())
                        .child("Rest")
                        .push()
                        .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });

        cloudfun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogPlus.dismiss();
                HashMap<String,Object> map=new HashMap<>();

                map.put("title",edit1.getText().toString());
                map.put("description",edit2.getText().toString());
                map.put("id",id);

                reference.child(FirebaseAuth.getInstance().getUid())
                        .child("Cloudfun")
                        .push()
                        .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(edit1.getText().toString())){
                    Toast.makeText(SendNotificationActivity.this, "enter title", Toast.LENGTH_SHORT).show();
                }

               else if (TextUtils.isEmpty(edit2.getText().toString())){
                    Toast.makeText(SendNotificationActivity.this, "enter notification", Toast.LENGTH_SHORT).show();
                }
               else {


                   dialogPlus.show();
                }
            }
        });
    }
}