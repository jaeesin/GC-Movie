package com.youknow.gcmovie.ui.nowplaying;


import com.youknow.gcmovie.data.model.Result;

import java.util.List;

public interface NowPlayingContract {
    interface View {
        void onMoviesLoaded(List<Result> results);

        void onError();

        void showProgressBar(int visibility);

        void hideError();
    }

    interface Presenter {
        void getMovies();

        void cancel();
    }
}
