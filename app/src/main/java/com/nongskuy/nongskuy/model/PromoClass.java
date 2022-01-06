package com.nongskuy.nongskuy.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.PromoData;

public class PromoClass {

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("promo")
    private List<PromoData> promo;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public List<PromoData> getPromo() {
        return promo;
    }

    public void setPromo(List<PromoData> promo) {
        this.promo = promo;
    }
}