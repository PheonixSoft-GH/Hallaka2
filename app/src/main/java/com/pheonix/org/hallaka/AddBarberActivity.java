package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.snackbar.Snackbar;

public class AddBarberActivity extends AppCompatActivity {

    ImageView image;
    EditText name, sTiming, cTiming;
    Button submit;

    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barber);

        image = findViewById(R.id.barberImageAddBarber);
        name = findViewById(R.id.barberNameAddBarber);
        sTiming = findViewById(R.id.barberSTimingAddBarber);
        cTiming = findViewById(R.id.barberCTimingAddBarber);
        submit = findViewById(R.id.addBarberId);
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
        String sSTiming = sTiming.getText().toString();
        String sCTiming = cTiming.getText().toString();

        if (!TextUtils.isEmpty(sName) && !TextUtils.isEmpty(sSTiming) && !TextUtils.isEmpty(sCTiming)) {

            spinKitView.setVisibility(View.VISIBLE);




        } else {
            showSnackBar("Enter required values in above fields");
            spinKitView.setVisibility(View.GONE);
        }

    }
    private void showSnackBar(String s) {
        Snackbar.make(findViewById(R.id.addBarberLay), s, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.colorBlue))
                .setTextColor(getResources().getColor(R.color.colorWhite))
                .show();
    }
}