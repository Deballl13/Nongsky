package com.nongskuy.nongskuy.model;

public class Promo {

    private Integer idToko;
    private String namaToko;
    private String gambarToko;
    private String alamatToko;
    private Double latToko;
    private Double longToko;
    private String namaMenu;
    private Integer hargaAwal;
    private String gambarMenu;
    private String jenisPromo;
    private Integer persentase;

    // recyclerview promo
    public Promo(Integer idToko, String namaToko, String gambarToko, String alamatToko, Double latToko,
                 Double longToko, String namaMenu, Integer hargaAwal, String gambarMenu,
                 String jenisPromo, Integer persentase) {
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.gambarToko = gambarToko;
        this.alamatToko = alamatToko;
        this.latToko = latToko;
        this.longToko = longToko;
        this.namaMenu = namaMenu;
        this.hargaAwal = hargaAwal;
        this.gambarMenu = gambarMenu;
        this.persentase = persentase;
        this.jenisPromo = jenisPromo;
    }

    public Integer getIdToko() {
        return idToko;
    }

    public void setIdToko(Integer idToko) {
        this.idToko = idToko;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public String getGambarToko() {
        return gambarToko;
    }

    public void setGambarToko(String gambarToko) {
        this.gambarToko = gambarToko;
    }

    public String getAlamatToko() {
        return alamatToko;
    }

    public void setAlamatToko(String alamatToko) {
        this.alamatToko = alamatToko;
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

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public Integer getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(Integer hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public String getGambarMenu() {
        return gambarMenu;
    }

    public void setGambarMenu(String gambarMenu) {
        this.gambarMenu = gambarMenu;
    }

    public String getJenisPromo() {
        return jenisPromo;
    }

    public void setJenisPromo(String jenisPromo) {
        this.jenisPromo = jenisPromo;
    }

    public Integer getPersentase() {
        return persentase;
    }

    public void setPersentase(Integer persentase) {
        this.persentase = persentase;
    }
}
