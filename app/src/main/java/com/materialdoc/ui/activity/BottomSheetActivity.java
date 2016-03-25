package com.materialdoc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.materialdoc.R;

/**
 * Created by subodhnijsure on 3/24/16.
 */
public class BottomSheetActivity  extends AppCompatActivity {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, BottomSheetActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_bottomsheet);

        final Button showBtn = (Button) findViewById(R.id.show_bottom_sheet_btn);
        final Button collapseBtn = (Button) findViewById(R.id.collapse_bottom_sheet_btn);
        collapseBtn.setEnabled(false);

        final View bottomSheetView = findViewById(R.id.bottom_sheet_layout_id);
        final BottomSheetBehavior bottomSheet = BottomSheetBehavior.from(bottomSheetView);

        bottomSheet.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events

            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheet != null) {
                    bottomSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                    showBtn.setEnabled(false);
                    collapseBtn.setEnabled(true);
                }
            }
        });

        collapseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheet != null) {
                    bottomSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    showBtn.setEnabled(true);
                    collapseBtn.setEnabled(false);
                }
            }
        });
    }
}
