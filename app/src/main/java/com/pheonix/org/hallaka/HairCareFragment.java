package com.pheonix.org.hallaka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pheonix.org.hallaka.Models.ProductDataModel;

import java.util.ArrayList;
import java.util.List;


public class HairCareFragment extends Fragment {
    RecyclerView recycler;
    activityHandler handler;
    View v;
    List<ProductDataModel> list = new ArrayList<>();

    public HairCareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_hair_care, container, false);
        recycler = v.findViewById(R.id.hairCareRecycler);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(manager);

        handler = new activityHandler(list);
        recycler.setAdapter(handler);

        return v;
    }

    public void onResume() {
        super.onResume();

        FirebaseDatabase.getInstance().getReference("products").orderByChild("tag").equalTo("Hair Care").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot d : snapshot.getChildren()) {
                        ProductDataModel model = d.getValue(ProductDataModel.class);
                        list.add(model);

                        handler.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}