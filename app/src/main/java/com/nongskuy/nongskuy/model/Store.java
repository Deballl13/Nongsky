package com.nongskuy.nongskuy.model;

public class Store {

    private Double rating;
    private String namaToko;
    private String alamatToko;
    private String tipeToko;
    private Double jarakToko;
    private String satuanJarak;

    public Store(Double rating, String namaToko, String alamatToko, String tipeToko, Double jarakToko, String satuanJarak) {
        this.rating = rating;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.tipeToko = tipeToko;
        this.jarakToko = jarakToko;
        this.satuanJarak = satuanJarak;
    }

    public Store(Double rating, String namaToko, String tipeToko, Double jarakToko, String satuanJarak) {
        this.rating = rating;
        this.namaToko = namaToko;
        this.tipeToko = tipeToko;
        this.jarakToko = jarakToko;
        this.satuanJarak = satuanJarak;
    }

    public Store(String namaToko, String alamatToko, Double jarakToko, String satuanJarak) {
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.jarakToko = jarakToko;
        this.satuanJarak = satuanJarak;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
    }

    public String getTipeToko() {
        return tipeToko;
    }

    public void setTipeToko(String tipeToko) {
        this.tipeToko = tipeToko;
    }

    public Double getJarakToko() {
        return jarakToko;
    }

    public void setJarakToko(Double jarakToko) {
        this.jarakToko = jarakToko;
    }

    public String getSatuanJarak() {
        return satuanJarak;
    }

    public void setSatuanJarak(String satuanJarak) {
        this.satuanJarak = satuanJarak;
    }
}
