package com.nongskuy.nongskuy.model;

public class Promo {

    private Integer idToko;
    private String namaToko;
    private String namaMenu;
    private Integer hargaAwal;
    private String gambar;
    private Integer persentase;
    private String jenis_promo;

    public Promo(Integer idToko, String namaToko, String namaMenu, Integer hargaAwal, String gambar, Integer persentase, String jenis_promo) {
        this.idToko = idToko;
        this.namaToko = namaToko;
        this.namaMenu = namaMenu;
        this.hargaAwal = hargaAwal;
        this.gambar = gambar;
        this.persentase = persentase;
        this.jenis_promo = jenis_promo;
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

    public String getGambar() { return gambar; }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Integer getPersentase() {
        return persentase;
    }

    public void setPersentase(Integer persentase) {
        this.persentase = persentase;
    }

    public String getJenis_promo() {
        return jenis_promo;
    }

    public void setJenis_promo(String jenis_promo) {
        this.jenis_promo = jenis_promo;
    }
}
