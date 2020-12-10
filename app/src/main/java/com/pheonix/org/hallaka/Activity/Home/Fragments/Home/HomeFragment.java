package com.pheonix.org.hallaka.Activity.Home.Fragments.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pheonix.org.hallaka.Activity.Booking.BookingActivity;
import com.pheonix.org.hallaka.Adapters.ViewAdapter;
import com.pheonix.org.hallaka.Models.UserDataModel;
import com.pheonix.org.hallaka.R;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    int NUM_PAGES;
    LinearLayout bookingLay;
    TextView userName, type;
    ViewPager viewPager;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase rel;
    ViewAdapter viewAdapter;
    private int currentPage;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        viewPager = view.findViewById(R.id.pager);

        Integer[] images = {R.drawable.poster1, R.drawable.book2};
        viewAdapter = new ViewAdapter(getContext(), images);
        viewPager.setAdapter(viewAdapter);
        viewPager.requestFocus();

        currentPage = 0;
        NUM_PAGES = images.length;


        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0;
                            }
                            viewPager.setCurrentItem(currentPage++, true);

                        }
                    });
                } catch (Exception ignore) {
                }
            }
        }, 100, 3000);


        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        bookingLay = view.findViewById(R.id.bookingLayHome);
        userName = view.findViewById(R.id.userNameHome);
        type = view.findViewById(R.id.typeHome);

        final Bundle s = getActivity().getIntent().getExtras();

        FirebaseDatabase.getInstance().getReference("users").child("profiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    UserDataModel userDataModel = d.getValue(UserDataModel.class);
                    if (userDataModel.getEmail().equals(s.getString("data"))) {
                        userName.setText(userDataModel.getName());
                        type.setText(userDataModel.getType());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showSnackBar(databaseError.getMessage());
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

    private void showSnackBar(String s) {
        Snackbar.make(getView().findViewById(R.id.homeFragLay), s, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.colorBlue))
                .setTextColor(getResources().getColor(R.color.colorWhite))
                .show();
    }
}