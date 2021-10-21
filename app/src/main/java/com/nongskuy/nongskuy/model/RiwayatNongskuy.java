package com.nongskuy.nongskuy.model;

public class RiwayatNongskuy {
    private String namaToko;
    private Integer statusPesan;
    private Integer totalKursi;
    private Integer totalDeposit;
    private String caraBayar;
    private String tglPesan;
    private String waktuPesan;

    public RiwayatNongskuy() {}

    public RiwayatNongskuy(String namaToko, Integer statusPesan, Integer totalKursi, Integer totalDeposit, String caraBayar, String tglPesan, String waktuPesan){
        this.namaToko = namaToko;
        this.statusPesan = statusPesan;
        this.totalKursi = totalKursi;
        this.totalDeposit = totalDeposit;
        this.caraBayar = caraBayar;
        this.tglPesan = tglPesan;
        this.waktuPesan = waktuPesan;
    }

    public String getNamaToko() {
        return namaToko;
    }

    public void setNamaToko(String namaToko) {
        this.namaToko = namaToko;
    }

    public Integer getStatusPesan() {
        return statusPesan;
    }

    public void setStatusPesan(Integer statusPesan) {
        this.statusPesan = statusPesan;
    }

    public Integer getTotalKursi() {
        return totalKursi;
    }

    public void setTotalKursi(Integer totalKursi) {
        this.totalKursi = totalKursi;
    }

    public Integer getTotalDeposit() {
        return totalDeposit;
    }

    public void setTotalDeposit(Integer totalDeposit) {
        this.totalDeposit = totalDeposit;
    }

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    public String getTglPesan() {
        return tglPesan;
    }

    public void setTglPesan(String tglPesan) {
        this.tglPesan = tglPesan;
    }

    public String getWaktuPesan() {
        return waktuPesan;
    }

    public void setWaktuPesan(String waktuPesan) {
        this.waktuPesan = waktuPesan;
    }
}
