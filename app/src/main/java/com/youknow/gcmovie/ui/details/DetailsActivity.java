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

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.youknow.gcmovie.R;
import com.youknow.gcmovie.data.model.MovieResp;
import com.youknow.gcmovie.data.source.TmdbServiceProvider;
import com.youknow.gcmovie.ui.adapter.GenreAdapter;

import static com.youknow.gcmovie.ui.main.MainActivity.MOVIE_ID;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private DetailsContract.Presenter detailsPresenter;

    private LottieAnimationView movieProgressBar;
    private TextView tvOverviewLabel;
    private TextView tvOverview;
    private TextView tvReleaseDate;
    private TextView tvRuntime;
    private TextView tvVoteAvg;
    private TextView tvTagline;
    private TextView tvErrMessage;
    private ImageView ivPoster;
    private RecyclerView rvGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        movieProgressBar = findViewById(R.id.movieProgressBar);
        tvOverviewLabel = findViewById(R.id.tvOverviewLabel);
        tvOverview = findViewById(R.id.tvOverview);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvRuntime = findViewById(R.id.tvRuntime);
        tvVoteAvg = findViewById(R.id.tvVoteAvg);
        tvTagline = findViewById(R.id.tvTagline);
        tvErrMessage = findViewById(R.id.tvErrMessage);
        ivPoster = findViewById(R.id.ivPoster);
        rvGenres = findViewById(R.id.rvGenres);

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
