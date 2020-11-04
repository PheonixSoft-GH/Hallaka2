package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.ybq.android.spinkit.SpinKitView;

public class SalonActivity extends AppCompatActivity {

    SpinKitView spinKitView;
    Button addBarber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

        spinKitView = findViewById(R.id.spin_kit);
        addBarber = findViewById(R.id.AddBarberIdSalon);

        addBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SalonActivity.this,AddBarberActivity.class);
                startActivity(intent);

            }
        });

    }
}