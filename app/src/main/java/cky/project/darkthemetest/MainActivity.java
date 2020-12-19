package cky.project.darkthemetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int prevUIFlag;
    private Button new_activity, new_fragment;
    private TextView activity_title;
    private ThemeHelper themeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        themeHelper = new ThemeHelper(this, this);

        new_activity = findViewById(R.id.activity_new_activity);
        new_fragment = findViewById(R.id.activity_new_fragment);
        activity_title = findViewById(R.id.activity_title);


        int currentMode = themeHelper.getCurrentMode();
        if (currentMode == -1) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            changeTheme(themeHelper.DAY);
            themeHelper.saveCurrentMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(currentMode);
            changeTheme(currentMode);
        }

        prevUIFlag = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        new_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        new_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // AND operator
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (prevUIFlag != nightModeFlags) {
            prevUIFlag = nightModeFlags;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
                // change to light mode
                changeTheme(themeHelper.DAY);
            } else {
                // change to dark mode
                changeTheme(themeHelper.NIGHT);
            }
        }
    }

    private void changeTheme(int state) {

        if (state == themeHelper.DAY) {
            themeHelper.toLightMode();
            activity_title.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));

        } else {
            themeHelper.toDarkMode();
            activity_title.setTextColor(ContextCompat.getColor(this, R.color.colorText_dark_mode));

        }

    }

}