package com.youknow.gcmovie.ui.upcoming;

import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.Result;
import com.youknow.gcmovie.data.source.TmdbServiceProvider;
import com.youknow.gcmovie.ui.adapter.MoviesAdapter;
import com.youknow.gcmovie.ui.details.DetailsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingFragment extends Fragment implements UpcomingContract.View, MoviesAdapter.MovieClickListener {

    private UpcomingContract.Presenter mPresenter;

    @BindView(R.id.tvErrMessage) TextView tvErrMessage;
    @BindView(R.id.rvMovies) RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        mPresenter = new UpcomingPresenter(this, TmdbServiceProvider.getService());

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        moviesAdapter = new MoviesAdapter(getContext(), this);
        rvMovies.setAdapter(moviesAdapter);
        rvMovies.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.grid_layout_columns)));

        mPresenter.getMovies();
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
    public void onMovieClicked(String title) {
        startActivity(new Intent(getContext(), DetailsActivity.class).putExtra("MOVIE", title));
    }
}
