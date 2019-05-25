package com.youknow.gcmovie.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
class Result {
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("release_date")
    private String releaseDate;
    private int id;
    private String title;
    @SerializedName("vote_average")
    private float voteAverage;
}
