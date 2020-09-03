package com.example.technicaltest.network.property;

import com.squareup.moshi.Json;

public class MovieProperty {
    public int id;
    public String name;
    public String description;
    public ImageProperty image;
    public String rating;
    @Json(name = "release_date")
    public String releaseDate;
    public String budget;
    @Json(name = "total_revenue")
    public String totalRevenue;
}
