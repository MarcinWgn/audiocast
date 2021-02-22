package com.example.audiocast;

public class RadioStation {

    private final String name;
    private final String uri;
    private final String img;

    public RadioStation(String name, String url, String img) {
        this.name = name;
        this.uri = url;
        this.img = img;
    }
    public RadioStation(String name, String url) {
        this.name = name;
        this.uri = url;
        this.img = null;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public String getImg() {
        return img;
    }
}
