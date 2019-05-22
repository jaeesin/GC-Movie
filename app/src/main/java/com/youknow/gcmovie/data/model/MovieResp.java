package com.youknow.gcmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class MovieResp {
    private boolean adult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    private int budget;
    private List<Genre> genres;
    private String homepage;
    private int id;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    private String overview;
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    private String releaseDate;
    private long revenue;
    private int runtime;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private int voteCount;
}

