package com.android.Law.adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.android.Law.fragment.DowloadFragment;
import com.android.Law.fragment.FollowFragment;
import com.android.Law.fragment.SeenFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FollowFragment();

            case 1:
                return new DowloadFragment();

            case 2:
                return new SeenFragment();

            default:
                return new FollowFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Đang theo dõi";
                break;

            case 1:
                title = "Đã tải";
                break;

            case 2:
                title = "Đã xem";
                break;

        }
        return title;
    }


}
