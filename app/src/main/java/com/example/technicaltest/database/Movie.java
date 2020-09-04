package com.example.technicaltest.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

import java.io.Serializable;

@Entity(tableName = "MOVIE")
public class Movie implements Serializable {
    @PrimaryKey
    public int id;
    public String name;
    public String description;
    @ColumnInfo(name = "thumb_image_url")
    public String thumbImageUrl;
    @ColumnInfo(name = "post_image_url")
    public String posterImageUrl;
    public String rating;
    @ColumnInfo(name = "runtime")
    public String runTime;
    public String budget;
    @ColumnInfo(name = "box_office_revenue")
    public String boxOfficeRevenue;
    @ColumnInfo(name = "total_revenue")
    public String totalRevenue;

    public Movie(){

    }

    public Movie(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbImageUrl='" + thumbImageUrl + '\'' +
                ", posterImageUrl='" + posterImageUrl + '\'' +
                ", rating='" + rating + '\'' +
                ", runTime='" + runTime + '\'' +
                ", budget='" + budget + '\'' +
                ", boxOfficeRevenue='" + boxOfficeRevenue + '\'' +
                ", totalRevenue='" + totalRevenue + '\'' +
                '}';
    }
}