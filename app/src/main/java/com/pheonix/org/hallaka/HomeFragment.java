package com.pheonix.org.hallaka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pheonix.org.hallaka.Models.UserDataModel;

public class HomeFragment extends Fragment {

    LinearLayout bookingLay;
    TextView userName, type;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase rel;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        bookingLay = view.findViewById(R.id.bookingLayHome);
        userName = view.findViewById(R.id.userNameHome);
        type = view.findViewById(R.id.typeHome);

        final Bundle s = getActivity().getIntent().getExtras();

        rel.getInstance().getReference("users").child("profiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    UserDataModel userDataModel=d.getValue(UserDataModel.class);
                    if(userDataModel.getEmail().equals(s.getString("data"))) {
                        userName.setText(userDataModel.getName());
                        type.setText(userDataModel.getType());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        bookingLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), BookingActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }

}