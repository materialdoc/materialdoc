package com.materialdoc.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.materialdoc.ui.fragment.TabFragment;

public class TabIconAdapter extends FragmentStatePagerAdapter {

    private int mCount;

    public TabIconAdapter(@NonNull FragmentManager manager, int count) {
        super(manager);
        mCount = count;
    }


    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance();
    }
}
