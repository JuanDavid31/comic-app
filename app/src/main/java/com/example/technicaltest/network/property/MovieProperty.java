package com.example.technicaltest.network.property;

import com.squareup.moshi.Json;

public class MovieProperty {
    public int id;
    public String name;
    public String description = "";
    public ImageProperty image;
    public String rating;
    @Json(name = "runtime")
    public String runTime;
    public String budget;
    @Json(name = "box_office_revenue")
    public String boxOfficeRevenue;
    @Json(name = "total_revenue")
    public String totalRevenue;

    public MovieProperty(int id, String name){
        this.id = id;
        this.name = name;
        this.image = new ImageProperty();
    }
}
