package com.nongskuy.nongskuy.model;

public class Promo {

    private String namaMakanan;
    private String namaToko;
    private Integer hargaAwal;
    private Integer hargaPromo;
    private String keterangan;

    public Promo(String namaMakanan, String namaToko, Integer hargaAwal, Integer hargaPromo, String keterangan) {
        this.namaMakanan = namaMakanan;
        this.namaToko = namaToko;
        this.hargaAwal = hargaAwal;
        this.hargaPromo = hargaPromo;
        this.keterangan = keterangan;
    }

    public Promo(String namaMakanan, String namaToko, String keterangan) {
        this.namaMakanan = namaMakanan;
        this.namaToko = namaToko;
        this.keterangan = keterangan;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public Integer getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(Integer hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public Integer getHargaPromo() {
        return hargaPromo;
    }

    public void setHargaPromo(Integer hargaPromo) {
        this.hargaPromo = hargaPromo;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
