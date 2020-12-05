package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pheonix.org.hallaka.Models.SalonDataModel;
import com.pheonix.org.hallaka.Utils.Funcs;
import com.squareup.picasso.Picasso;

public class SalonActivity extends AppCompatActivity {

    SpinKitView spinKitView;
    Button addBarber;
    TextView sName, sCity;
    ImageView sCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);

        sName = findViewById(R.id.salonNameSalon);
        sCity = findViewById(R.id.salonCitySalon);
        sCover = findViewById(R.id.salonImageSalon);
        spinKitView = findViewById(R.id.spin_kit);
        addBarber = findViewById(R.id.AddBarberIdSalon);
        spinKitView.setVisibility(View.VISIBLE);
        Funcs.disableCurrentScreen(getActivity());

        FirebaseDatabase.getInstance().getReference("saloons").orderByChild("ownerId").equalTo(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    spinKitView.setVisibility(View.GONE);
                    Funcs.enableCurrentScreen(getActivity());
                    for(DataSnapshot d:snapshot.getChildren()) {
                        SalonDataModel sData = d.getValue(SalonDataModel.class);
                        setValues(sData);
                    }
//
                }
                else {
                    showSnackBar("Saloon not found!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                spinKitView.setVisibility(View.GONE);
                Funcs.enableCurrentScreen(getActivity());
                showSnackBar(error.getMessage());
            }
        });

        addBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SalonActivity.this, AddBarberActivity.class);
                startActivity(intent);

            }
        });

    }

    private Activity getActivity() {
        return SalonActivity.this;
    }

    private void setValues(SalonDataModel sData) {
        sName.setText(sData.getName());
        sCity.setText(sData.getCity());
        Picasso.get().load(sData.getImage()).priority(Picasso.Priority.HIGH).placeholder(R.drawable.loading_img).into(sCover);

    }

    private void showSnackBar(String s) {
        Snackbar.make(findViewById(R.id.salonActLay), s, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.colorBlue))
                .setTextColor(getResources().getColor(R.color.colorWhite))
                .show();
    }
}