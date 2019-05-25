package com.youknow.gcmovie.ui.upcoming;

import com.youknow.gcmovie.BuildConfig;
import com.youknow.gcmovie.data.model.MoviesResp;
import com.youknow.gcmovie.data.source.TmdbService;
import com.youknow.gcmovie.data.source.TmdbServiceProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingPresenter implements UpcomingContract.Presenter {

    private UpcomingContract.View mView;
    private TmdbService mTmdbService;

    public UpcomingPresenter(UpcomingContract.View view) {
        mView = view;
        mTmdbService = TmdbServiceProvider.getService();
    }

    @Override
    public void getMovies() {
        mTmdbService.getNowPlayings(BuildConfig.API_KEY).enqueue(new Callback<MoviesResp>() {
            @Override
            public void onResponse(Call<MoviesResp> call, Response<MoviesResp> response) {
                if (response.isSuccessful()) {
                    mView.onMoviesLoaded(response.body().getResults());
                } else {
                    mView.onError();
                }
            }

            @Override
            public void onFailure(Call<MoviesResp> call, Throwable t) {
                mView.onError();
            }
        });
    }
}
