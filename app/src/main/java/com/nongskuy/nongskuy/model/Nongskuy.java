package com.nongskuy.nongskuy.model;

public class Nongskuy {

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
    private String jarakToko;
    private Double latToko;
    private Double longToko;
    private Integer idMetodeBayar;
    private String namaMetodeBayar;
    private String no_rek;

    // recyclerview populer
    public Nongskuy(Integer idToko, String gambarToko, String namaToko, String alamatToko, String tipeToko,
                    String jarakToko, String ratingToko, Double latToko, Double longToko) {
        this.idToko = idToko;
        this.gambarToko = gambarToko;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.tipeToko = tipeToko;
        this.jarakToko = jarakToko;
        this.ratingToko = ratingToko;
        this.longToko = longToko;
        this.latToko = latToko;
    }

    //recyclerview pencarian
    public Nongskuy(Integer idToko, String gambarToko, String namaToko, String alamatToko,
                    String jarakToko, Double latToko, Double longToko) {
        this.idToko = idToko;
        this.gambarToko = gambarToko;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.jarakToko = jarakToko;
        this.longToko = longToko;
        this.latToko = latToko;
    }

    //recyclerview beranda terdekat
    public Nongskuy(Integer idToko, String gambarToko, String namaToko, String alamatToko, String tipeToko, String jarakToko,
                    Double latToko, Double longToko) {
        this.idToko = idToko;
        this.gambarToko = gambarToko;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.tipeToko = tipeToko;
        this.jarakToko = jarakToko;
        this.latToko = latToko;
        this.longToko = longToko;
    }

    //detail nongskuy
    public Nongskuy(Integer idToko, String gambarToko, String namaToko, String alamatToko, String tipeToko, String noHpToko, String igToko,
                    String webToko, String hariOpsToko, Double latToko, Double longToko){
        this.idToko = idToko;
        this.gambarToko = gambarToko;
        this.namaToko = namaToko;
        this.alamatToko = alamatToko;
        this.tipeToko = tipeToko;
        this.noHpToko = noHpToko;
        this.igToko = igToko;
        this.webToko = webToko;
        this.hariOpsToko = hariOpsToko;
        this.latToko = latToko;
        this.longToko = longToko;
    }

    //recycler view fasilitas
    public Nongskuy(String fasilitasToko){
        this.fasilitasToko = fasilitasToko;
    }

    // recycler view metode bayar
    public Nongskuy(Integer idMetodeBayar, String namaMetodeBayar, String no_rek){
        this.idMetodeBayar = idMetodeBayar;
        this.namaMetodeBayar = namaMetodeBayar;
        this.no_rek = no_rek;
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

    public String getJarakToko() {
        return jarakToko;
    }

    public void setJarakToko(String jarakToko) {
        this.jarakToko = jarakToko;
    }

    public String getRatingToko() {
        return ratingToko;
    }

    public void setRatingToko(String ratingToko) {
        this.ratingToko = ratingToko;
    }

    public Double getLatToko() {
        return latToko;
    }

    public void setLatToko(Double latToko) {
        this.latToko = latToko;
    }

    public Double getLongToko() {
        return longToko;
    }

    public void setLongToko(Double longToko) {
        this.longToko = longToko;
    }

    public Integer getIdMetodeBayar() {
        return idMetodeBayar;
    }

    public void setIdMetodeBayar(Integer idMetodeBayar) {
        this.idMetodeBayar = idMetodeBayar;
    }

    public String getNamaMetodeBayar() {
        return namaMetodeBayar;
    }

    public void setNamaMetodeBayar(String namaMetodeBayar) {
        this.namaMetodeBayar = namaMetodeBayar;
    }

    public String getNo_rek() {
        return no_rek;
    }

    public void setNo_rek(String no_rek) {
        this.no_rek = no_rek;
    }
}
