package com.youknow.gcmovie.ui.upcoming;

import com.youknow.gcmovie.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class UpcomingFragment extends Fragment implements UpcomingContract.View {

    private UpcomingContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        mPresenter = new UpcomingPresenter(this);
        return rootView;
    }

}
