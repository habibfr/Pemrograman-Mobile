package com.habibfr.data_mhs_jmp.model;

import java.io.Serializable;

public class Mahasiswa implements Serializable {
    int id;
    String nomor, nama, tglLahir, jenisKelamin, alamat;

    public Mahasiswa() {
    }

    public Mahasiswa(int id, String nomor, String nama, String tglLahir, String jenisKelamin, String alamat) {
        this.id = id;
        this.nomor = nomor;
        this.nama = nama;
        this.tglLahir = tglLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
