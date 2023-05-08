package com.myaccounting_21410100050;

import java.io.Serializable;
import java.util.Date;

public class DataTrx {
    private String id, uraian;
    private Date tglTransaksi;
    private int debit, kredit;

    public DataTrx(String id, String uraian, Date tglTransaksi, int debit, int kredit) {
        this.id = id;
        this.uraian = uraian;
        this.tglTransaksi = tglTransaksi;
        this.debit = debit;
        this.kredit = kredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public Date getTglTransaksi() {
        return tglTransaksi;
    }

    public void setTglTransaksi(Date tglTransaksi) {
        this.tglTransaksi = tglTransaksi;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getKredit() {
        return kredit;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }
}
