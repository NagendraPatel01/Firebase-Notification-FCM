package com.example.fcmcloudemessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.HashSet;

public class RagisterActivity extends AppCompatActivity {

    EditText name,email,password;
    Button ragistration;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ragister);

        login=findViewById(R.id.login);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        ragistration=findViewById(R.id.ragistration);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RagisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        ragistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())){

                    Toast.makeText(RagisterActivity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(RagisterActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                }

                else  if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){

                    Toast.makeText(RagisterActivity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(RagisterActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }

                else if (password.getText().toString().length()<6){

                    Toast.makeText(RagisterActivity.this, "please enter 6 digit or more then password", Toast.LENGTH_SHORT).show();
                }

                else {

                    ragister();
                }
            }

            private void ragister() {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                HashMap<String,Object> map=new HashMap<>();

                                map.put("username",name.getText().toString());
                                map.put("useremail",email.getText().toString());
                                map.put("password",password.getText().toString());

                                FirebaseDatabase.getInstance().getReference()
                                        .child("Users")
                                        .child(FirebaseAuth.getInstance().getUid())
                                        .setValue(map)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                startActivity(new Intent(RagisterActivity.this,MainActivity.class));
                                                finish();
                                                Toast.makeText(RagisterActivity.this, "Ragister successfull", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                Toast.makeText(RagisterActivity.this, "Ragister successfull", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(RagisterActivity.this, "fail rasgister", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}