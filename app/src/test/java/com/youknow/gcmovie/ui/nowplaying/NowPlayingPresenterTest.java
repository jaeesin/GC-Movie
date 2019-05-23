package com.youknow.gcmovie.ui.nowplaying;

import android.view.View;

import com.youknow.gcmovie.BuildConfig;
import com.youknow.gcmovie.data.model.MoviesResp;
import com.youknow.gcmovie.data.model.Result;
import com.youknow.gcmovie.data.source.TmdbService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

import static org.mockito.Mockito.when;

public class NowPlayingPresenterTest {

    @Mock
    private NowPlayingContract.View mockView;
    @Mock
    private TmdbService mockTmdbService;

    private NowPlayingContract.Presenter mPresenter;
    private InOrder inOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new NowPlayingPresenter(mockView, mockTmdbService);
        inOrder = Mockito.inOrder(mockView);
    }

    @Test
    public void getMoviesTest() {
        final List<Result> mockResult = Arrays.asList(
                new Result("test1.jpg", "1", 1, "test1", 10f),
                new Result("test2.jpg", "2", 2, "test2", 9f),
                new Result("test3.jpg", "3", 3, "test3", 5.5f)
        );

        when(mockTmdbService.getNowPlayings(BuildConfig.API_KEY)).thenReturn(new Call<MoviesResp>() {
            @Override
            public Response<MoviesResp> execute() {
                return null;
            }

            @Override
            public void enqueue(Callback<MoviesResp> callback) {
                callback.onResponse(this, Response.success(new MoviesResp(mockResult)));
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<MoviesResp> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        });

        mPresenter.getMovies();

        inOrder.verify(mockView).showProgressBar(View.VISIBLE);
        inOrder.verify(mockView).hideError();
        inOrder.verify(mockView).showProgressBar(View.GONE);
        inOrder.verify(mockView).onMoviesLoaded(mockResult);
    }

    @Test
    public void getMoviesTest_error() {
        when(mockTmdbService.getNowPlayings(BuildConfig.API_KEY)).thenReturn(new Call<MoviesResp>() {
            @Override
            public Response<MoviesResp> execute() {
                return null;
            }

            @Override
            public void enqueue(Callback<MoviesResp> callback) {
                callback.onFailure(this, new HttpException(Response.<MoviesResp>error(404, ResponseBody.create(MediaType.get("test/test"), ""))));
            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<MoviesResp> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        });

        mPresenter.getMovies();

        inOrder.verify(mockView).showProgressBar(View.VISIBLE);
        inOrder.verify(mockView).hideError();
        inOrder.verify(mockView).showProgressBar(View.GONE);
        inOrder.verify(mockView).onError();
    }
}
