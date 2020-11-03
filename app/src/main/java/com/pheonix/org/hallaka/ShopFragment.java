package com.pheonix.org.hallaka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class ShopFragment extends Fragment {

    ViewPager myViewPagerShop;
    TabLayout myTabLayoutShop;
    TabsAccessAdapterShop myTabsAccessAdapterShop;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        myTabLayoutShop = view.findViewById(R.id.mainTabsShop);
        myViewPagerShop = view.findViewById(R.id.mainTab_pagesShop);
        myTabsAccessAdapterShop = new TabsAccessAdapterShop(getFragmentManager());
        myViewPagerShop.setAdapter(myTabsAccessAdapterShop);
        myTabLayoutShop.setupWithViewPager(myViewPagerShop);

        return view;
    }
}