package cky.project.darkthemetest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

public class ThemeHelper {

    public final int DAY = AppCompatDelegate.MODE_NIGHT_NO;
    public final int NIGHT = AppCompatDelegate.MODE_NIGHT_YES;
    private String preferenceType, modeName;
    private SharedPreferences sharedPreferences;
    private Activity activity;
    private Context context;

    public ThemeHelper(Activity activity, Context context) {
        preferenceType = "DarkThemeTest";
        modeName = "mode";
        sharedPreferences = context.getSharedPreferences(preferenceType, Context.MODE_PRIVATE);
        this.activity = activity;
        this.context = context;
    }

    public void toLightMode() {
        Window window = activity.getWindow();

        window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark_light_mode));
        ((AppCompatActivity) activity).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorPrimary_light_mode)));
        window.getDecorView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
    }

    public void toDarkMode() {
        Window window = activity.getWindow();

        window.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark_dark_mode));
        ((AppCompatActivity) activity).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.colorPrimary_dark_mode)));
        window.getDecorView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorDarkGrey));

    }

    public int getCurrentMode() {
        return sharedPreferences.getInt(modeName, -1);
    }

    public void saveCurrentMode(int mode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(modeName, mode);
        editor.commit();
    }
}
