package com.example.audiocast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Stations {
    static final List<RadioStation> STATION_LIST = new ArrayList<>(Arrays.asList(
            new RadioStation("Nowy Swiat","https://stream.nowyswiat.online/mp3", "https://nowyswiat.online/wp-content/uploads/2020/09/fala-140x140.png", "https://nowyswiat.online/ramowka/"),
            new RadioStation("Kraków","https://stream3.nadaje.com:9117/radiokrakow", "https://www.radiokrakow.pl/application/site/web/images/logo.png", "https://www.radiokrakow.pl/ramowka/"),
            new RadioStation("TOK FM","http://wroclaw.radio.pionier.net.pl:8000/pl/tuba10-1.mp3","https://bi.im-g.pl/im/2/22623/m22623812.png","https://audycje.tokfm.pl/ramowka"),
            new RadioStation("RMF Classic","https://rs102-krk.rmfstream.pl/rmf_classic","https://www.rmfclassic.pl/static/frontend/images/logo-classic.png","https://www.rmfclassic.pl/radio/ramowka"),
            new RadioStation("357","https://stream.rcs.revma.com/ye5kghkgcm0uv", "https://d9hwwxbc8j0dw.cloudfront.net/public/authors/399444/4a1eb7b0-b926-4018-a8d6-13b61878c90e.jpg", "https://radio357.pl/ramowka"),
            new RadioStation("HALO","https://s3.radio.co/s6b9e36d1c/listen", "https://halo.radio/wp-content/uploads/2020/12/HaloRadio_Slogan_RGB.png", "https://halo.radio/ramowka/")
    ));
}
