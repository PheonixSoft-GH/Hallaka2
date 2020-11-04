package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSalonActivity extends AppCompatActivity {

    ImageView image;
    EditText name, city;
    Button submit;

    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_salon);

        image = findViewById(R.id.salonImageAddSalon);
        name = findViewById(R.id.salonNameAddSalon);
        city = findViewById(R.id.salonCityAddSalon);
        submit = findViewById(R.id.addSalonId);
        spinKitView = findViewById(R.id.spin_kit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Submit();

            }
        });

    }

    public void Submit() {
        final String sName = name.getText().toString();
        String sCity = city.getText().toString();

        if (!TextUtils.isEmpty(sName) && !TextUtils.isEmpty(sCity)) {

            spinKitView.setVisibility(View.VISIBLE);




        } else {
            Toast.makeText(AddSalonActivity.this, "Enter required values in above fields", Toast.LENGTH_LONG).show();
            spinKitView.setVisibility(View.GONE);
        }

    }

}