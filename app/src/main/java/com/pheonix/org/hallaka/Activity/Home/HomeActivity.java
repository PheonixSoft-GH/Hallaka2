package com.pheonix.org.hallaka.Activity.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.pheonix.org.hallaka.Adapters.TabsAccessAdapterHome;
import com.pheonix.org.hallaka.R;

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