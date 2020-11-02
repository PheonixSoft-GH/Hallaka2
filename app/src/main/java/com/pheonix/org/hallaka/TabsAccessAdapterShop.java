package com.pheonix.org.hallaka;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessAdapterShop extends FragmentPagerAdapter {

    public TabsAccessAdapterShop(FragmentManager fm) {
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
            case 2:

            case 3:


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Wax";
            case 1:
                return "Shop";
            case 2:
                return "Home";
            case 3:
                return "dd";

            default:
                return null;
        }
    }
}
