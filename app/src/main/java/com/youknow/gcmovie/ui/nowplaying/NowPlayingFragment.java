package com.youknow.gcmovie.ui.nowplaying;

import com.youknow.gcmovie.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class NowPlayingFragment extends Fragment implements NowPlayingContract.View {

    private NowPlayingContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        mPresenter = new NowPlayingPresenter(this);
        return rootView;
    }

}
