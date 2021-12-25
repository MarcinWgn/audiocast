package com.example.audiocast;

public class RadioStation {

    private final String name;
    private final String uri;
    private final String img;
    private final String page;

    public RadioStation(String name, String url, String img, String page) {
        this.name = name;
        this.uri = url;
        this.img = img;
        this.page = page;
    }
    public RadioStation(String name, String url, String page) {
        this.name = name;
        this.uri = url;
        this.page = page;
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

    public String getPage() {return page; }

}
