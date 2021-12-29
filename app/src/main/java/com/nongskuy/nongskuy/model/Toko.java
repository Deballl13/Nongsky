package com.nongskuy.nongskuy.model;

public class Toko {

    private Integer idToko;
    private String gambarToko;
    private String namaToko;
    private String alamatToko;
    private String tipeToko;
    private String noHpToko;
    private String igToko;
    private String webToko;
    private String hariOpsToko;
    private String fasilitasToko;
    private String ratingToko;
    private Double jarakToko;
    private String satuanJarak;

    // recyclerview populer
    public Toko(Integer idToko, String gambarToko, String namaToko, String alamatToko, String tipeToko,
                Double jarakToko, String ratingToko) {
        this.idToko = idToko;
        this.gambarToko = gambarToko;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.tipeToko = tipeToko;
        this.jarakToko = jarakToko;
        this.ratingToko = ratingToko;
    }

    //recyclerview pencarian
    public Toko(Integer idToko, String gambarToko, String namaToko, String alamatToko, Double jarakToko) {
        this.idToko = idToko;
        this.gambarToko = gambarToko;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.jarakToko = jarakToko;
    }

    //recyclerview beranda terdekat
    public Toko(String namaToko, String tipeToko, Double jarakToko, String satuanJarak) {
        this.namaToko = namaToko;
        this.tipeToko = tipeToko;
        this.jarakToko = jarakToko;
        this.satuanJarak = satuanJarak;
    }

    public Integer getIdToko() {
        return idToko;
    }

    public void setIdToko(Integer idToko) {
        this.idToko = idToko;
    }

    public String getGambarToko() {
        return gambarToko;
    }

    public void setGambarToko(String gambarToko) {
        this.gambarToko = gambarToko;
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

    public String getNoHpToko() {
        return noHpToko;
    }

    public void setNoHpToko(String noHpToko) {
        this.noHpToko = noHpToko;
    }

    public String getIgToko() {
        return igToko;
    }

    public void setIgToko(String igToko) {
        this.igToko = igToko;
    }

    public String getWebToko() {
        return webToko;
    }

    public void setWebToko(String webToko) {
        this.webToko = webToko;
    }

    public String getHariOpsToko() {
        return hariOpsToko;
    }

    public void setHariOpsToko(String hariOpsToko) {
        this.hariOpsToko = hariOpsToko;
    }

    public String getFasilitasToko() {
        return fasilitasToko;
    }

    public void setFasilitasToko(String fasilitasToko) {
        this.fasilitasToko = fasilitasToko;
    }

    public String getRatingToko() {
        return ratingToko;
    }

    public void setRating(String ratingToko) {
        this.ratingToko = ratingToko;
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
