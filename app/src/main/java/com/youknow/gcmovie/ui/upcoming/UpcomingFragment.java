package com.youknow.gcmovie.ui.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.Result;
import com.youknow.gcmovie.data.source.TmdbServiceProvider;
import com.youknow.gcmovie.ui.adapter.MoviesAdapter;

import java.util.List;

public class UpcomingFragment extends Fragment implements UpcomingContract.View {

    private UpcomingContract.Presenter mPresenter;

    private RecyclerView rvMovies;
    private LottieAnimationView moviesProgressBar;
    private TextView tvErrMessage;
    private MoviesAdapter moviesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movies, container, false);
        mPresenter = new UpcomingPresenter(this, TmdbServiceProvider.getService());

        rvMovies = root.findViewById(R.id.rvMovies);
        moviesProgressBar = root.findViewById(R.id.moviesProgressBar);
        tvErrMessage = root.findViewById(R.id.tvErrMessage);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesAdapter = new MoviesAdapter(getContext());
        rvMovies.setAdapter(moviesAdapter);
        rvMovies.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_layout_columns)));

        mPresenter.getMovies();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.cancel();
    }

    @Override
    public void onMoviesLoaded(List<Result> movies) {
        moviesAdapter.setMovies(movies);
    }

    @Override
    public void onError() {
        tvErrMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressBar(int visibility) {
        moviesProgressBar.setVisibility(visibility);
    }

    @Override
    public void hideError() {
        tvErrMessage.setVisibility(View.GONE);
    }
}
