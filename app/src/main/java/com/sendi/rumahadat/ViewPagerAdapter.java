package com.sendi.rumahadat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new CardFragment();
        }
        else if (position == 1)
        {
            fragment = new GridFragment();
        }
        else if (position == 2)
        {
            fragment = new ListFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
//        if (position == 0)
//        {
//            title = "Card";
//        }
//        else if (position == 1)
//        {
//            title = "Grid";
//        }
//        else if (position == 2)
//        {
//            title = "List";
//        }
        return title;
    }
}