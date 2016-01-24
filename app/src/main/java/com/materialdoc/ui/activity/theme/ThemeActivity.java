package com.materialdoc.ui.activity.theme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.materialdoc.R;

public class ThemeActivity extends AppCompatActivity {

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, ThemeActivity.class);
    activity.startActivity(intent);
  }
  public static void start(@NonNull Activity activity, Class<? extends ThemeActivity> launchActivity) {
    Intent intent = new Intent(activity, launchActivity);
    activity.startActivity(intent);
    activity.finish();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.ac_theme);

    findViewById(R.id.btnThemeIndigo).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        start(ThemeActivity.this, ThemeActivityIndigo.class);
      }
    });

    findViewById(R.id.btnThemeOrange).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        start(ThemeActivity.this, ThemeActivityOrange.class);
      }
    });

    findViewById(R.id.btnThemeGreen).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        start(ThemeActivity.this, ThemeActivityGreen.class);
      }
    });
  }
}
