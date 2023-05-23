package com.habibfr.cek_ongkir;

public class City {
    String city_id;
    String province_id;
    String province;
    String type;
    String city_name;
    String postal_code;

    public City() {
    }

    public City(String city_id, String province_id, String province, String type, String city_name, String postal_code) {
        this.city_id = city_id;
        this.province_id = province_id;
        this.province = province;
        this.type = type;
        this.city_name = city_name;
        this.postal_code = postal_code;
    }

    public String getCity_id() {
        return city_id;
    }

    public String getProvince_id() {
        return province_id;
    }

    public String getProvince() {
        return province;
    }

    public String getType() {
        return type;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getPostal_code() {
        return postal_code;
    }
}
