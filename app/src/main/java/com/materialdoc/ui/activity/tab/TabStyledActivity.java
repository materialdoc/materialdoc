package com.materialdoc.ui.activity.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.materialdoc.R;
import com.materialdoc.ui.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabStyledActivity extends AppCompatActivity {

    protected TabLayout mTabLayout;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, TabStyledActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tabs_styled);
        initViews();
    }

    private FragmentStatePagerAdapter getAdapter() {
        List<String> titleList = new ArrayList<>();
        titleList.add("Item One");
        titleList.add("Item Two");
        titleList.add("Item Three");

        return new TabAdapter(getSupportFragmentManager(), titleList);
    }

    private void initViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(getAdapter());

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(viewPager);
    }
}
