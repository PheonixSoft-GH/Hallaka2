package com.pheonix.org.hallaka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    ViewPager myViewPager;
    TabLayout myTabLayout;
    TabsAccessAdapterHome myTabsAccessAdapterHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myTabLayout = findViewById(R.id.mainTabs);
        myViewPager = findViewById(R.id.mainTab_pages);

        myTabsAccessAdapterHome = new TabsAccessAdapterHome(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessAdapterHome);

        myTabLayout.setupWithViewPager(myViewPager);

    }
}