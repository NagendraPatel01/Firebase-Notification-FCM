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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView ragister;
    EditText email,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        ragister=findViewById(R.id.ragister);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);

        ragister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,RagisterActivity.class));
                finish();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(email.getText().toString())){
                    Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }

                else  if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){

                    Toast.makeText(LoginActivity.this, "please enter valid email", Toast.LENGTH_SHORT).show();
                }


                else if(TextUtils.isEmpty(password.getText().toString())){

                    Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                }
                else if (password.getText().toString().length()<6){

                    Toast.makeText(LoginActivity.this, "please enter 6 digit or more then password", Toast.LENGTH_SHORT).show();
                }

                else {

                    logincode();
                }
            }

            private void logincode() {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                Toast.makeText(LoginActivity.this, "login success full", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(LoginActivity.this, "failer login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}