package com.materialdoc.ui.activity.tab;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.materialdoc.ui.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabMultiActivity extends TabIconActivity {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, TabMultiActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected FragmentStatePagerAdapter getAdapter() {
        List<String> titleList = new ArrayList<>();
        titleList.add("Item One");
        titleList.add("Item Two");
        titleList.add("Item Three");

        return new TabAdapter(getSupportFragmentManager(), titleList);
    }
}
