package com.example.sawara.tarek.Ex4_Networking;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesResponse {

    @SerializedName("data")
    @Expose
    public Data data;

    public static class Data {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("description")
        @Expose
        public Object description;
        @SerializedName("datetime")
        @Expose
        public Integer datetime;
        @SerializedName("images")
        @Expose
        public List<Image> images = null;

    }


    public static class Image {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("title")
        @Expose
        public Object title;
        @SerializedName("description")
        @Expose
        public Object description;
        @SerializedName("datetime")
        @Expose
        public Integer datetime;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("animated")
        @Expose
        public Boolean animated;
        @SerializedName("width")
        @Expose
        public Integer width;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("size")
        @Expose
        public Integer size;
        @SerializedName("views")
        @Expose
        public Integer views;
        @SerializedName("bandwidth")
        @Expose
        public Integer bandwidth;
        @SerializedName("link")
        @Expose
        public String link;

    }

}



