package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.ybq.android.spinkit.SpinKitView;

public class SalonActivity extends AppCompatActivity {

    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

        spinKitView = findViewById(R.id.spin_kit);

    }
}