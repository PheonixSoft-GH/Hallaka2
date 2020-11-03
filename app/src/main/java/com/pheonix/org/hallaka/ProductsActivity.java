package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView products;
    FloatingActionButton addProduct;
    List<Integer> count = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        products = findViewById(R.id.productIdProducts);
        addProduct = findViewById(R.id.addProductIdProducts);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        products.setLayoutManager(layoutManager);

        for (int i = 0; i < 30; i++) {

            count.add(i);
        }

        if (count != null) {
            activityHandler handler = new activityHandler(count);
            products.setAdapter(handler);
        }

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProductsActivity.this,AddProductActivity.class);
                startActivity(intent);

            }
        });


    }
}