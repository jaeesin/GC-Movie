package com.youknow.gcmovie.ui.details;

import android.view.View;

import com.youknow.gcmovie.BuildConfig;
import com.youknow.gcmovie.data.model.MovieResp;
import com.youknow.gcmovie.data.source.TmdbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsPresenter implements DetailsContract.Presenter {

    private final DetailsContract.View view;
    private final TmdbService tmdbService;
    private Call<MovieResp> call;

    DetailsPresenter(DetailsContract.View view, TmdbService tmdbService) {
        this.view = view;
        this.tmdbService = tmdbService;
    }

    @Override
    public void getMovie(String movieId) {
        call = tmdbService.getMovie(movieId, BuildConfig.API_KEY);
        call.enqueue(new Callback<MovieResp>() {
            @Override
            public void onResponse(Call<MovieResp> call, Response<MovieResp> response) {
                view.onMovieLoaded(response.body());
            }

            @Override
            public void onFailure(Call<MovieResp> call, Throwable t) {
                view.showProgressBar(View.GONE);
                view.onError();
            }
        });
    }

    @Override
    public void cancel() {
        if (call != null) {
            call.cancel();
        }
    }
}
