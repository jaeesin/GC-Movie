package com.youknow.gcmovie.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.youknow.gcmovie.ui.nowplaying.NowPlayingFragment;
import com.youknow.gcmovie.ui.upcoming.UpcomingFragment;

class BottomNavigationAdapter extends FragmentStatePagerAdapter {

    BottomNavigationAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NowPlayingFragment();
            default:
                return new UpcomingFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
