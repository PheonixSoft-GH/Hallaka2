package com.pheonix.org.hallaka;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class HomeActivity extends AppCompatActivity {

    ViewPager myViewPagerHome,myViewPagerShop;
    TabLayout myTabLayoutHome,myTabLayoutShop;
    TabsAccessAdapterHome tabsAccessAdapterHome;
    TabsAccessAdapterShop tabsAccessAdapterShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myTabLayoutHome = findViewById(R.id.mainTabs);
        myViewPagerHome = findViewById(R.id.mainTab_pages);
        myViewPagerShop = findViewById(R.id.mainTab_pagesShop);
        myViewPagerShop = findViewById(R.id.mainTab_pagesShop);

        tabsAccessAdapterHome = new TabsAccessAdapterHome(getSupportFragmentManager());
        myViewPagerHome.setAdapter(tabsAccessAdapterHome);
        myTabLayoutHome.setupWithViewPager(myViewPagerHome);

        tabsAccessAdapterShop = new TabsAccessAdapterShop(getSupportFragmentManager());
        myViewPagerShop.setAdapter(tabsAccessAdapterShop);
        myTabLayoutShop.setupWithViewPager(myViewPagerShop);

    }
}
