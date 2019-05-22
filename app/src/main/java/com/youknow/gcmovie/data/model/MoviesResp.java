package com.youknow.gcmovie.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoviesResp {
    private int page;
    private List<Result> results;
    private Dates dates;
    private int totalPages;
    private int totalResults;
}

