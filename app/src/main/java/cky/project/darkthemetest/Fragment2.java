package cky.project.darkthemetest;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {

    private TextView fragment2_title;
    private ThemeHelper themeHelper;
    private int prevUIFlag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_layout, container, false);

        themeHelper = new ThemeHelper(getActivity(), getContext());
        fragment2_title = view.findViewById(R.id.fragment2_title);

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
            fragment2_title.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark_light_mode));

        } else {
            themeHelper.toDarkMode();
            fragment2_title.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorText_dark_mode));
        }

    }
}
