package com.youknow.gcmovie.ui.main;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.youknow.gcmovie.R;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "movie_id";

    @BindView(R.id.mainViewPager) ViewPager mainViewPager;
    @BindView(R.id.bottomNavigation) BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainViewPager.setAdapter(new BottomNavigationAdapter(getSupportFragmentManager()));

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_now_playing:
                    mainViewPager.setCurrentItem(0);
                    return true;
                case R.id.action_upcoming:
                    mainViewPager.setCurrentItem(1);
                    return true;
                default:
                    return false;
            }
        });

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
