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
                WaxFragment waxFragment = new WaxFragment();
                return waxFragment;
            case 1:
                SprayFragment sprayFragment = new SprayFragment();
                return sprayFragment;
            case 2:
                HairCareFragment hairCareFragment = new HairCareFragment();
                return hairCareFragment;
            case 3:
                BodyCareFragment bodyCareFragment = new BodyCareFragment();
                return bodyCareFragment;

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
                return "Spray";
            case 2:
                return "Hair Care";
            case 3:
                return "Body Care";


            default:
                return null;
        }
    }
}
