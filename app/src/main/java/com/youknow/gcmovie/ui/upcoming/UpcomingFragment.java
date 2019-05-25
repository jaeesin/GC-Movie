package com.youknow.gcmovie.ui.upcoming;

import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.Result;
import com.youknow.gcmovie.ui.adapter.MoviesAdapter;

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

public class UpcomingFragment extends Fragment implements UpcomingContract.View {

    private UpcomingContract.Presenter mPresenter;

    private RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;
    private TextView tvErrMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        mPresenter = new UpcomingPresenter(this);

        rvMovies = rootView.findViewById(R.id.rvMovies);
        tvErrMessage = rootView.findViewById(R.id.tvErrMessage);

        return rootView;
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
    public void onMoviesLoaded(List<Result> movies) {
        moviesAdapter.setMovies(movies);
    }

    @Override
    public void onError() {
        tvErrMessage.setVisibility(View.VISIBLE);
    }
}
