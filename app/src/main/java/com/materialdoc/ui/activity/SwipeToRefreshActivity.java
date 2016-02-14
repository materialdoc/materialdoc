package com.materialdoc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import com.materialdoc.R;

public class SwipeToRefreshActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefreshLayout;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, SwipeToRefreshActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_swipe_to_refresh);

        initViews();
    }

    private void initViews() {
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.petrol_100));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void refreshData() {
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(false);
        }
    }
}

