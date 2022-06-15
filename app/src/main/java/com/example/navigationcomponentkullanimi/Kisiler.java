package com.example.navigationcomponentkullanimi;

import java.io.Serializable;

public class Kisiler implements Serializable {
    private int kisi_no;
    private String kisi_ad;

    public Kisiler() {
    }

    public Kisiler(int kisi_no, String kisi_ad) {
        this.kisi_no = kisi_no;
        this.kisi_ad = kisi_ad;
    }

    public int getKisi_no() {
        return kisi_no;
    }

    public void setKisi_no(int kisi_no) {
        this.kisi_no = kisi_no;
    }

    public String getKisi_ad() {
        return kisi_ad;
    }

    public void setKisi_ad(String kisi_ad) {
        this.kisi_ad = kisi_ad;
    }
}
