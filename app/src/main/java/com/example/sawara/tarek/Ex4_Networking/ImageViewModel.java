package com.example.sawara.tarek.Ex4_Networking;

public class ImageViewModel {

    private String id;
    private String imageUrl;

    public ImageViewModel(String id, String link) {
        this.id = id;
        this.imageUrl = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
