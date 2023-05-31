package com.habibfr.gojek_clone;

public class CardImage {
    int banner;
    String title;
    String desc;

    public CardImage(int banner, String title, String desc) {
        this.banner = banner;
        this.title = title;
        this.desc = desc;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int banner) {
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
