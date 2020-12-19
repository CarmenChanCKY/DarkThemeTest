package cky.project.darkthemetest;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainActivity3Adapter extends FragmentStateAdapter {

    private Fragment tabFragment;
    private Fragment[] tabFragmentList;

    public MainActivity3Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        tabFragment = null;
        tabFragmentList = new Fragment[2];
        tabFragmentList[0] = null;
        tabFragmentList[1] = null;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (tabFragmentList[position] != null) {
            tabFragment = tabFragmentList[position];
        } else {
            if (position == 0) {
                tabFragment = new Fragment1();
            } else {
                tabFragment = new Fragment2();
            }

            tabFragmentList[position] = tabFragment;
        }

        return tabFragment;
    }

    @Override
    public int getItemCount() {
        return tabFragmentList.length;
    }
}
