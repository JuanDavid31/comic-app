package com.example.technicaltest.network.property;

import com.squareup.moshi.Json;

import java.util.List;

public class ResponseProperty {
    @Json(name = "results")
    public List<MovieProperty> movies;
}
