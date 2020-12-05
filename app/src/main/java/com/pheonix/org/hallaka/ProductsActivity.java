package com.pheonix.org.hallaka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pheonix.org.hallaka.Models.ProductDataModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView products;
    FloatingActionButton addProduct;
    List<ProductDataModel> count = new ArrayList<>();
    activityHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        products = findViewById(R.id.productIdProducts);
        addProduct = findViewById(R.id.addProductIdProducts);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        products.setLayoutManager(layoutManager);

        handler = new activityHandler(count);
        products.setAdapter(handler);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProductsActivity.this, AddProductActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        FirebaseDatabase.getInstance().getReference("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    count.clear();
                    for (DataSnapshot d : snapshot.getChildren()) {
                        ProductDataModel dataModel = d.getValue(ProductDataModel.class);
                        count.add(dataModel);
                    }
                }
                handler.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}