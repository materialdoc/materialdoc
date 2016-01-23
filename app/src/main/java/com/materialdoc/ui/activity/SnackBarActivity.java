package com.materialdoc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.materialdoc.R;

public class SnackBarActivity extends AppCompatActivity {

    private Snackbar mSnackbar;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, SnackBarActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_snack_bar);

        findViewById(R.id.btnShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar();
            }
        });

        findViewById(R.id.btnShowStyled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStyledSnackbar();
            }
        });

        findViewById(R.id.btnHide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSnackbar();
            }
        });
    }

    private void showSnackbar() {
        mSnackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), "No network connection.", Snackbar.LENGTH_SHORT);
        mSnackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // retry to send email here
            }
        });
        mSnackbar.show();
    }

    private void showStyledSnackbar() {
        mSnackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), "No network connection.", Snackbar.LENGTH_SHORT);
        mSnackbar.setActionTextColor(getResources().getColor(R.color.indigo));
        mSnackbar.setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // retry to send email here
            }
        });

        View snackbarView = mSnackbar.getView();
        int snackbarTextId = android.support.design.R.id.snackbar_text;

        TextView textView = (TextView) snackbarView.findViewById(snackbarTextId);
        textView.setTextColor(getResources().getColor(R.color.indigo));

        snackbarView.setBackgroundColor(getResources().getColor(R.color.pink));
        mSnackbar.show();
    }

    private void hideSnackbar() {
        if (mSnackbar != null) {
            mSnackbar.dismiss();
        }
    }
}
