package com.habibfr.gojek_clone;

public class Resto {
    int cover;
    String title;
    String jenis;
    String rating;

    public Resto(int cover, String title, String jenis, String rating) {
        this.cover = cover;
        this.title = title;
        this.jenis = jenis;
        this.rating = rating;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
