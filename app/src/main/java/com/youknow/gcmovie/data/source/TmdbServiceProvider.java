package com.youknow.gcmovie.data.source;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbServiceProvider {

    private static TmdbService tmdbService = null;

    private TmdbServiceProvider() {
    }

    public static synchronized TmdbService getService() {
        if (tmdbService == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(
                            new HttpLoggingInterceptor()
                                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            tmdbService = retrofit.create(TmdbService.class);
        }

        return tmdbService;
    }

}
