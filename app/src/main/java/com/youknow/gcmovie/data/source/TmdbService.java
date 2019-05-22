package com.youknow.gcmovie.data.source;


import com.youknow.gcmovie.data.model.MoviesResp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("/3/movie/now_playing")
    Call<MoviesResp> getNowPlayings(@Query("api_key") String apiKey);

    @GET("/3/movie/upcoming")
    Call<MoviesResp> getUpcomings(@Query("api_key") String apiKey);

}
