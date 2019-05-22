package com.youknow.gcmovie.ui.details;


import com.youknow.gcmovie.data.model.MovieResp;

public interface DetailsContract {
    interface View {
        void showProgressBar(int visibility);
        void onMovieLoaded(MovieResp movie);
        void onError();
        void hideError();
    }

    interface Presenter {
        void getMovie(String movieId);

        void cancel();
    }
}
