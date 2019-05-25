package com.youknow.gcmovie.ui.upcoming;

public class UpcomingPresenter implements UpcomingContract.Presenter {

    private UpcomingContract.View mView;

    public UpcomingPresenter(UpcomingContract.View view) {
        mView = view;
    }
}
