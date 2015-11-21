package com.materialdoc.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.materialdoc.ui.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitleList;

    public TabAdapter(@NonNull FragmentManager manager, @NonNull List<String> titleList) {
        super(manager);
        mTitleList = new ArrayList<>();
        mTitleList.addAll(titleList);
    }


    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (!mTitleList.isEmpty()) {
            return mTitleList.get(position);
        } else {
            return null;
        }
    }
}

