package com.pheonix.org.hallaka;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessAdapterHome extends FragmentPagerAdapter {

    public TabsAccessAdapterHome(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                ShopFragment shopFragment = new ShopFragment();
                return shopFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Shop";

            default:
                return null;
        }
    }
}
