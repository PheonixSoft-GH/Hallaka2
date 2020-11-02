package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ViewPager myViewPager;
    TabLayout myTabLayout;
    TabsAccessAdapter myTabsAccessAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myTabLayout = findViewById(R.id.mainTabs);
        myViewPager = findViewById(R.id.mainTab_pages);

        myTabsAccessAdapter = new TabsAccessAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessAdapter);

        myTabLayout.setupWithViewPager(myViewPager);

    }
}