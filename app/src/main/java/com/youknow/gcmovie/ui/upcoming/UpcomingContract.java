package com.youknow.gcmovie.ui.upcoming;

import com.youknow.gcmovie.data.model.Result;

import java.util.List;

public interface UpcomingContract {
    interface View {
        void onMoviesLoaded(List<Result> results);

        void onError();
    }

    interface Presenter {
        void getMovies();
    }
}
