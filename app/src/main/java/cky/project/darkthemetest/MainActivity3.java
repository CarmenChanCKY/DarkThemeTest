package cky.project.darkthemetest;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity3 extends AppCompatActivity implements TriggerTabUpdate {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private TabLayoutMediator tabLayoutMediator;
    private MainActivity3Adapter adapter;
    private ThemeHelper themeHelper;
    private int prevUIFlag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);

        viewPager = findViewById(R.id.view_pager);
        adapter = new MainActivity3Adapter(this);
        tabLayout = findViewById(R.id.tab_bar);

        themeHelper = new ThemeHelper(this, this);

        viewPager.setAdapter(adapter);

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        prevUIFlag = nightModeFlags;

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
            // current: light mode
            changeTheme(themeHelper.DAY);
        } else {
            changeTheme(themeHelper.NIGHT);
        }

        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, tabConfigurationStrategy);
        tabLayoutMediator.attach();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

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
            tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary_light_mode));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorTab_light_mode));
        } else {
            themeHelper.toDarkMode();
            tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary_dark_mode));
            tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorTab_dark_mode));
        }
    }

    private TabLayoutMediator.TabConfigurationStrategy tabConfigurationStrategy = new TabLayoutMediator.TabConfigurationStrategy() {
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            switch (position) {
                case 0:
                    tab.setText("Fragment 1");
                    break;
                case 1:
                    tab.setText("Fragment 2");
                    break;
            }
        }
    };

    @Override
    public void updateTab(int type) {
        changeTheme(type);
    }


}
