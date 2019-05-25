package com.youknow.gcmovie.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.MovieResp;
import com.youknow.gcmovie.data.source.TmdbServiceProvider;
import com.youknow.gcmovie.ui.adapter.GenreAdapter;

import static com.youknow.gcmovie.ui.main.MainActivity.MOVIE_ID;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private DetailsContract.Presenter detailsPresenter;

    @BindView(R.id.movieProgressBar) LottieAnimationView movieProgressBar;
    @BindView(R.id.tvOverviewLabel) TextView tvOverviewLabel;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.tvRuntime) TextView tvRuntime;
    @BindView(R.id.tvVoteAvg) TextView tvVoteAvg;
    @BindView(R.id.tvTagline) TextView tvTagline;
    @BindView(R.id.tvErrMessage) TextView tvErrMessage;
    @BindView(R.id.ivPoster) ImageView ivPoster;
    @BindView(R.id.rvGenres) RecyclerView rvGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (!intent.hasExtra(MOVIE_ID)) {
            finish();
            return;
        }

        String movieId = intent.getStringExtra(MOVIE_ID);

        detailsPresenter = new DetailsPresenter(this, TmdbServiceProvider.getService());
        detailsPresenter.getMovie(movieId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        detailsPresenter.cancel();
    }

    @Override
    public void showProgressBar(int visibility) {
        movieProgressBar.setVisibility(visibility);
    }

    @Override
    public void onMovieLoaded(MovieResp movie) {
        tvOverviewLabel.setVisibility(View.VISIBLE);

        rvGenres.setAdapter(new GenreAdapter(movie.getGenres()));
        rvGenres.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getPosterPath()).into(ivPoster);
        tvOverview.setText(movie.getOverview());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvRuntime.setText(getString(R.string.runtime_args, movie.getRuntime()));
        tvVoteAvg.setText(String.valueOf(movie.getVoteAverage()));
        tvTagline.setText("\"" + movie.getTagline() + "\"");
    }

    @Override
    public void onError() {
        tvErrMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        tvErrMessage.setVisibility(View.GONE);
    }
}
