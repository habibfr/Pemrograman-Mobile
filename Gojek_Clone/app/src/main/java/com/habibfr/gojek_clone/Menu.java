package com.habibfr.gojek_clone;

public class Menu {
    String name;
    int imgSrc;

    public Menu(String name, int imgSrc) {
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public String getName() {
        return name;
    }

    public int getImgSrc() {
        return imgSrc;
    }
}
