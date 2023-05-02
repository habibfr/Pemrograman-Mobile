package com.uts_pbm;

import java.io.Serializable;
import java.util.ArrayList;

public class DataTrx implements Serializable {
    private String tangal, uraian, jenis;
    private int harga;

    public DataTrx(String tangal, String uraian, String jenis, int harga) {
        this.tangal = tangal;
        this.uraian = uraian;
        this.jenis = jenis;
        this.harga = harga;
    }

    public String getTangal() {
        return tangal;
    }

    public void setTangal(String tangal) {
        this.tangal = tangal;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
