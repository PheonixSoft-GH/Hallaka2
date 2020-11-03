package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

public class BarberActivity extends AppCompatActivity {

    ImageView imageView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        imageView = findViewById(R.id.menuBtnBarber);
        drawerLayout = findViewById(R.id.drawerLayBarber);
        navigationView = findViewById(R.id.navLayBarber);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(navigationView);

            }
        });

    }
}