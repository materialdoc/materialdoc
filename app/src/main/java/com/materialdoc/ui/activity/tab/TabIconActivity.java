package com.materialdoc.ui.activity.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.materialdoc.R;
import com.materialdoc.ui.adapter.TabIconAdapter;

public class TabIconActivity extends TabActivity {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, TabIconActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mTabLayout != null) {
            initTabIcons(mTabLayout);
        }
    }

    @Override
    protected FragmentStatePagerAdapter getAdapter() {
        int tabsCount = 3;
        return new TabIconAdapter(getSupportFragmentManager(), tabsCount);
    }

    private void initTabIcons(@NonNull TabLayout tabLayout) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                setTabIconByPosition(tab, i);
            }
        }
    }

    private void setTabIconByPosition(@NonNull TabLayout.Tab tab, int position) {
        switch (position) {
            case 0:
                tab.setIcon(R.drawable.selector_call);
                break;
            case 1:
                tab.setIcon(R.drawable.selector_favorite);
                break;
            case 2:
                tab.setIcon(R.drawable.selector_person);
                break;
        }
    }
}
