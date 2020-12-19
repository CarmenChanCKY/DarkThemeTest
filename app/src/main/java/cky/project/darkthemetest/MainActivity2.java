package cky.project.darkthemetest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class MainActivity2 extends AppCompatActivity {
    private int prevUIFlag;
    private Button activity2_dark, activity2_light;
    private ThemeHelper themeHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        themeHelper = new ThemeHelper(this, this);

        activity2_dark = findViewById(R.id.activity2_dark);
        activity2_light = findViewById(R.id.activity2_light);

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        prevUIFlag = nightModeFlags;

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
            // current: light mode
            changeTheme(themeHelper.DAY);
        } else {
            changeTheme(themeHelper.NIGHT);
        }

        activity2_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // dark mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (nightModeFlags != prevUIFlag) {
                    prevUIFlag = nightModeFlags;
                    changeTheme(themeHelper.NIGHT);
                    themeHelper.saveCurrentMode(themeHelper.NIGHT);
                }

            }
        });

        activity2_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // light mode
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (nightModeFlags != prevUIFlag) {
                    prevUIFlag = nightModeFlags;
                    changeTheme(themeHelper.DAY);
                    themeHelper.saveCurrentMode(themeHelper.DAY);
                }

            }
        });
    }

    private void changeTheme(int state) {

        if (state == themeHelper.DAY) {
            themeHelper.toLightMode();
        } else {
            themeHelper.toDarkMode();
        }
    }
}
