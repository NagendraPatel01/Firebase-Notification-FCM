package com.example.fcmcloudemessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button button;
    FrameLayout frame;
    BottomNavigationView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        frame=findViewById(R.id.frame);
        bottom=findViewById(R.id.bottom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                finish();

                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        });

        bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.alluser:
                        replace(new AlluserFragment());
                        break;

                    case R.id.topic:
                        replace(new TopicFragment());
                        break;

                    case R.id.notification:
                        replace(new NotificationFragment());
                        break;

                    case R.id.profile:
                        replace(new ProfileFragment());
                        break;

                }
                return true;
            }
        });
    }

    void replace(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commit();
    }
}