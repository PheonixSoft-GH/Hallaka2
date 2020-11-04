package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddSalonActivity extends AppCompatActivity {

    EditText name,city;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_salon);

        name = findViewById(R.id.salonNameAddSalon);
        city = findViewById(R.id.salonCityAddSalon);
        submit = findViewById(R.id.addSalonId);

    }
}