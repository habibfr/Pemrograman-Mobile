package com.habibfr.cek_ongkir;

public class Provinsi {
    String province_id;
    String province;

    public Provinsi() {
    }

    public Provinsi(String province_id, String province) {
        this.province_id = province_id;
        this.province = province;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
