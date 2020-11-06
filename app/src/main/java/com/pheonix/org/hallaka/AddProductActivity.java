package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;

public class AddProductActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image;
    EditText name, price;
    Button submit;
    SpinKitView spinKitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        image = findViewById(R.id.productImageAddPro);
        name = findViewById(R.id.productNameAddPro);
        price = findViewById(R.id.productPriceAddPro);
        submit = findViewById(R.id.addProductId);
        spinKitView = findViewById(R.id.spin_kit);

        submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == submit) {
            String sName = name.getText().toString();
            String sPrice = price.getText().toString();

            if (!TextUtils.isEmpty(sName) && !TextUtils.isEmpty(sPrice)) {
                spinKitView.setVisibility(View.VISIBLE);


            } else {
                Toast.makeText(AddProductActivity.this, "Enter required values in above fields", Toast.LENGTH_LONG).show();
                spinKitView.setVisibility(View.GONE);
            }

        }
    }
}