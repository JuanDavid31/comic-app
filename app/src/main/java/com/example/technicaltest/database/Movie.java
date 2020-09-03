package com.example.technicaltest.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "MOVIE")
public class Movie implements Serializable {
    @PrimaryKey
    public int id;
    String name;
    String description;
    @ColumnInfo(name = "thumb_image_url")
    String thumbImageUrl;
    @ColumnInfo(name = "post_image_url")
    String posterImageUrl;

    public Movie(){

    }

    public Movie(int id, String name, String description, String thumbUrl, String smallUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbImageUrl = thumbUrl;
        this.posterImageUrl = smallUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbImageUrl='" + thumbImageUrl + '\'' +
                ", posterImageUrl='" + posterImageUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public void setThumbImageUrl(String thumbImageUrl) {
        this.thumbImageUrl = thumbImageUrl;
    }

    public String getPosterImageUrl() {
        return posterImageUrl;
    }

    public void setPosterImageUrl(String posterImageUrl) {
        this.posterImageUrl = posterImageUrl;
    }
}