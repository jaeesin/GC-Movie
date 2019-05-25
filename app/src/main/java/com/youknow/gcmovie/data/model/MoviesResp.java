package com.youknow.gcmovie.data.model;

import java.util.List;

import lombok.Data;

@Data
public class MoviesResp {
    private int page;
    private List<Result> results;
    private int totalPages;
    private int totalResults;
}

