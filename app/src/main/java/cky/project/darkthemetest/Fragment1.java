package cky.project.darkthemetest;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {
    private Switch fragment_switcher;
    private TextView fragment_title;
    private ThemeHelper themeHelper;
    private int prevUIFlag;
    private static TriggerTabUpdate triggerTabUpdate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_layout, container, false);
        themeHelper = new ThemeHelper(getActivity(), getContext());

        fragment_switcher = view.findViewById(R.id.fragment_switcher);
        fragment_title = view.findViewById(R.id.fragment_title);

        fragment_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment_switcher.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    changeTheme(themeHelper.NIGHT);
                    themeHelper.saveCurrentMode(themeHelper.NIGHT);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    changeTheme(themeHelper.DAY);
                    themeHelper.saveCurrentMode(themeHelper.DAY);
                }
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        prevUIFlag = nightModeFlags;

        if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
            // current: light mode
            changeTheme(themeHelper.DAY);

        } else {
            changeTheme(themeHelper.NIGHT);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TriggerTabUpdate) {
            triggerTabUpdate = (TriggerTabUpdate) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (prevUIFlag != nightModeFlags) {
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
                // current: light mode
                changeTheme(themeHelper.DAY);
            } else {
                changeTheme(themeHelper.NIGHT);
            }
        }
    }

    private void changeTheme(int state) {
        if (state == themeHelper.DAY) {
            themeHelper.toLightMode();
            fragment_title.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText_light_mode));
            fragment_switcher.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText_light_mode));
            themeHelper.saveCurrentMode(themeHelper.DAY);
            fragment_switcher.setChecked(false);

        } else {
            themeHelper.toDarkMode();
            fragment_title.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText_dark_mode));
            fragment_switcher.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText_dark_mode));
            themeHelper.saveCurrentMode(themeHelper.NIGHT);
            fragment_switcher.setChecked(true);
        }

        triggerTabUpdate.updateTab(state);

    }

}
