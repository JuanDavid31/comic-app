package com.example.technicaltest.network.property;

import com.squareup.moshi.Json;

public class MovieProperty {
    public int id;
    public String name;
    public String description = "";
    public ImageProperty image;
    public String rating;
    @Json(name = "runTime")
    public String runTime;
    public String budget;
    @Json(name = "box_office_revenue")
    public String boxOfficeRevenue;
    @Json(name = "total_revenue")
    public String totalRevenue;
}