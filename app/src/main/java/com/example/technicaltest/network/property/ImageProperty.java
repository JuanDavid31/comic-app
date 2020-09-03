package com.example.technicaltest.network.property;

import com.squareup.moshi.Json;

public class ImageProperty {
    @Json(name = "thumb_url")
    public String thumbUrl;
    @Json(name = "small_url")
    public String smallUrl;
}
