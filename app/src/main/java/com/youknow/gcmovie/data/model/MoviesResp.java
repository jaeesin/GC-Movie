package com.youknow.gcmovie.data.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoviesResp {
    private List<Result> results;
}

