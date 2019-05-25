package com.youknow.gcmovie.ui.nowplaying;

public class NowPlayingPresenter implements NowPlayingContract.Presenter {

    private NowPlayingContract.View mView;

    public NowPlayingPresenter(NowPlayingContract.View view) {
        mView = view;
    }
}
