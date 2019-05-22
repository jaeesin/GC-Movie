package com.youknow.gcmovie.ui.upcoming;

import android.view.View;

import com.youknow.gcmovie.BuildConfig;
import com.youknow.gcmovie.data.model.MoviesResp;
import com.youknow.gcmovie.data.source.TmdbService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class UpcomingPresenter implements UpcomingContract.Presenter {

    private UpcomingContract.View mView;
    private TmdbService mTmdbService;
    private Call<MoviesResp> mCall;

    UpcomingPresenter(UpcomingContract.View view, TmdbService tmdbService) {
        mView = view;
        mTmdbService = tmdbService;
    }

    @Override
    public void getMovies() {
        mView.showProgressBar(View.VISIBLE);
        mView.hideError();

        mCall = mTmdbService.getUpcomings(BuildConfig.API_KEY);
        mCall.enqueue(new Callback<MoviesResp>() {
            @Override
            public void onResponse(Call<MoviesResp> call, Response<MoviesResp> response) {
                mView.showProgressBar(View.GONE);
                mView.onMoviesLoaded(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MoviesResp> call, Throwable t) {
                mView.showProgressBar(View.GONE);
                mView.onError();
            }
        });
    }

    @Override
    public void cancel() {
        if (mCall != null) {
            mCall.cancel();
        }
    }
}
