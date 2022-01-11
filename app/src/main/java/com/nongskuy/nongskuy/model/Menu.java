package com.nongskuy.nongskuy.model;

public class Menu {

    private Integer idMenu;
    private Integer idToko;
    private String namaMenu;
    private Integer harga;
    private String gambar;
    private String jenisPromo;
    private Integer persentase;

    public Menu(Integer idMenu, String namaMenu, String gambar, Integer harga, String jenisPromo, Integer persentase){
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.gambar = gambar;
        this.harga = harga;
        this.jenisPromo = jenisPromo;
        this.persentase = persentase;
    }

    public Menu(Integer idMenu, String namaMenu, String gambar, Integer harga){
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.gambar = gambar;
        this.harga = harga;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdToko() {
        return idToko;
    }

    public void setIdToko(Integer idToko) {
        this.idToko = idToko;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
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
