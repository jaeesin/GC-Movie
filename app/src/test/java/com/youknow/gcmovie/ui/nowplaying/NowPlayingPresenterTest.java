package com.youknow.gcmovie.ui.nowplaying;

import com.youknow.gcmovie.BuildConfig;
import com.youknow.gcmovie.data.model.MoviesResp;
import com.youknow.gcmovie.data.model.Result;
import com.youknow.gcmovie.data.source.TmdbService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NowPlayingPresenterTest {

    @Mock
    private NowPlayingContract.View mockView;
    @Mock
    private TmdbService mockTmdbService;

    private NowPlayingPresenter presenter;

    private List<Result> mockMovies = Arrays.asList(
            new Result("posterpath1", "2019-03-01", 10, "hello world1", 10f),
            new Result("posterpath2", "2019-03-02", 20, "hello world2", 9f),
            new Result("posterpath3", "2019-03-03", 30, "hello world3", 8f),
            new Result("posterpath4", "2019-03-04", 40, "hello world4", 7f),
            new Result("posterpath5", "2019-03-05", 50, "hello world5", 6f)
    );

    private MoviesResp mockMoviesResp = new MoviesResp(3, mockMovies, 10, 100);

    private Call<MoviesResp> mockResponse = new Call<MoviesResp>() {
        @Override
        public Response<MoviesResp> execute() {
            return null;
        }

        @Override
        public void enqueue(Callback<MoviesResp> callback) {
            callback.onResponse(this, Response.success(mockMoviesResp));
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
    };
    private Call<MoviesResp> mockErrorResponse = new Call<MoviesResp>() {
        @Override
        public Response<MoviesResp> execute() {
            return null;
        }

        @Override
        public void enqueue(Callback<MoviesResp> callback) {
            callback.onFailure(this, new HttpException(Response.<MoviesResp>error(500, ResponseBody.create(MediaType.get("test/error"), "ERROR 500"))));
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
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new NowPlayingPresenter(mockView, mockTmdbService);
    }

    @Test
    public void getMoviesTest_normal() {
        when(mockTmdbService.getNowPlayings(BuildConfig.API_KEY)).thenReturn(mockResponse);

        presenter.getMovies();

        verify(mockView).onMoviesLoaded(mockMovies);
    }

    @Test
    public void getMoviesTest_error() {
        when(mockTmdbService.getNowPlayings(BuildConfig.API_KEY)).thenReturn(mockErrorResponse);

        presenter.getMovies();

        verify(mockView).onError();
    }

}
