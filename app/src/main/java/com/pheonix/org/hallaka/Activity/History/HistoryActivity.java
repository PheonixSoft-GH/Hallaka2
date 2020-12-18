package com.pheonix.org.hallaka.Activity.History;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pheonix.org.hallaka.Adapters.HistoryHandler;
import com.pheonix.org.hallaka.Models.BookingDataModel;
import com.pheonix.org.hallaka.R;
import com.pheonix.org.hallaka.Utils.Funcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {
    SpinKitView spinKitView;
    RecyclerView historyRecycler;
    List<BookingDataModel> list = new ArrayList<>();
    HistoryHandler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        spinKitView = findViewById(R.id.spin_kit_history);
        historyRecycler = findViewById(R.id.historyRecycler);

        adapter = new HistoryHandler(list);

        historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        historyRecycler.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoadingBAr();
        FirebaseDatabase.getInstance().getReference("Bookings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot d : snapshot.getChildren()) {
                        BookingDataModel b = d.getValue(BookingDataModel.class);
                        if (!(Objects.requireNonNull(b).getStatus().equalsIgnoreCase("upcoming"))) {
                            if (b.getUid().equalsIgnoreCase(FirebaseAuth.getInstance().getUid())) {
                                list.add(b);
                            }
                        }
                    }
                    if (list.isEmpty()) {
                        findViewById(R.id.nothingHereHistory).setVisibility(View.VISIBLE);
                        hideLoadingBAr();
                    }
                    hideLoadingBAr();
                    adapter.notifyDataSetChanged();
                } else {
                    findViewById(R.id.nothingHereHistory).setVisibility(View.VISIBLE);
                    hideLoadingBAr();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                hideLoadingBAr();
            }
        });

    }

    private void hideLoadingBAr() {
        spinKitView.setVisibility(View.GONE);
        Funcs.enableCurrentScreen(this);
    }

    private void showLoadingBAr() {
        spinKitView.setVisibility(View.VISIBLE);
        Funcs.disableCurrentScreen(this);
    }
}