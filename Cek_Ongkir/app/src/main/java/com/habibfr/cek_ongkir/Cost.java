package com.habibfr.cek_ongkir;

public class Cost {
    String service, description, value, etd, note;

    public Cost() {
    }

    public Cost(String service, String description, String value, String etd) {
        this.service = service;
        this.description = description;
        this.value = value;
        this.etd = etd;
        this.note = "";
    }

    public Cost(String service, String description, String value, String etd, String note) {
        this.service = service;
        this.description = description;
        this.value = value;
        this.etd = etd;
        this.note = note;
    }

    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public String getEtd() {
        return etd;
    }

    public String getNote() {
        return note;
    }
}
