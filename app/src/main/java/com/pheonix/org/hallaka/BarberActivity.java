package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BarberActivity extends AppCompatActivity {

    ImageView imageView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    LinearLayout productsLay;
    LinearLayout salonLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber);

        imageView = findViewById(R.id.menuBtnBarber);
        drawerLayout = findViewById(R.id.drawerLayBarber);
        navigationView = findViewById(R.id.navLayBarber);

        productsLay = findViewById(R.id.productLayBarber);
        salonLay = findViewById(R.id.salonLayBarber);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.openDrawer(navigationView);

            }
        });

        productsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BarberActivity.this, ProductsActivity.class);
                startActivity(intent);

            }
        });

        salonLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("saloons").orderByChild("ownerId").equalTo(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            startActivity(new Intent(BarberActivity.this, SalonActivity.class));

                        } else {
                            addSaloonIntent();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }

    private void addSaloonIntent() {
        Intent intent = new Intent(BarberActivity.this, AddSalonActivity.class);
        startActivity(intent);
    }
}