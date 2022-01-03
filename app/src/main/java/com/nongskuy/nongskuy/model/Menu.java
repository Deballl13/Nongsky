package com.nongskuy.nongskuy.model;

public class Menu {

    private Integer idMenu;
    private Integer idToko;
    private String namaMenu;
    private Integer harga;
    private String gambar;

    public Menu(Integer idMenu, Integer idToko, String namaMenu, Integer harga){
        this.idMenu = idMenu;
        this.idToko = idToko;
        this.namaMenu = namaMenu;
        this.harga = harga;
        //this.gambar = gambar;
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


}
