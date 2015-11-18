package com.materialdoc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.materialdoc.R;

import java.util.Random;

public class LinearProgressActivity extends AppCompatActivity {

    private static final int PROGRESS_DELAY = 100;
    private static final int MAX_PROGRESS = 100;

    private ProgressBar mDeterminateProgress;
    private ProgressBar mDeterminateStyledProgress;

    private ProgressBar mBufferedProgress;
    private ProgressBar mBufferedStyledProgress;

    private ProgressBar mMultiProgress;
    private ProgressBar mMultiStyledProgress;

    private Handler mHandler;
    private int progress;
    private int secondaryProgress;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, LinearProgressActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_linear_progress);

        initViews();
        fillProgressBars();
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    private void initViews() {
        mHandler = new Handler();

        mDeterminateProgress = (ProgressBar) findViewById(R.id.determinateProgress);
        mDeterminateStyledProgress = (ProgressBar) findViewById(R.id.determinateStyledProgress);

        mBufferedProgress = (ProgressBar) findViewById(R.id.bufferedProgress);
        mBufferedStyledProgress = (ProgressBar) findViewById(R.id.bufferedStyledProgress);

        mMultiProgress = (ProgressBar) findViewById(R.id.multiProgress);
        mMultiStyledProgress = (ProgressBar) findViewById(R.id.multiStyledProgress);
    }

    private void fillProgressBars() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int newProgress = getNewProgress();
                progress += newProgress;
                secondaryProgress += (newProgress + 2);

                if (progress > 100) {
                    progress = 100;
                }

                if (secondaryProgress > 100) {
                    secondaryProgress = 100;
                }

                mDeterminateProgress.setProgress(progress);
                mDeterminateStyledProgress.setProgress(progress);

                mBufferedProgress.setProgress(progress);
                mBufferedStyledProgress.setProgress(progress);

                mMultiProgress.setProgress(progress);
                mMultiStyledProgress.setProgress(progress);

                if (secondaryProgress <= MAX_PROGRESS) {
                    mBufferedProgress.setSecondaryProgress(secondaryProgress);
                    mBufferedStyledProgress.setSecondaryProgress(secondaryProgress);
                }

                if (progress <= MAX_PROGRESS) {
                    fillProgressBars();
                } else {
                    mMultiProgress.setIndeterminate(true);
                    mMultiStyledProgress.setIndeterminate(true);
                }

            }
        }, PROGRESS_DELAY);
    }

    private int getNewProgress() {
        Random random = new Random();
        return random.nextInt(5);
    }
}
