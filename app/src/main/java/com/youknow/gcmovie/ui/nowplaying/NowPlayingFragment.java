package com.youknow.gcmovie.ui.nowplaying;

import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.Result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NowPlayingFragment extends Fragment implements NowPlayingContract.View {

    private NowPlayingContract.Presenter mPresenter;

    private TextView tvErrMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        mPresenter = new NowPlayingPresenter(this);

        tvErrMessage = rootView.findViewById(R.id.tvErrMessage);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.getMovies();
    }

    @Override
    public void onMoviesLoaded(List<Result> results) {

    }

    @Override
    public void onError() {
        tvErrMessage.setVisibility(View.VISIBLE);
    }
}
