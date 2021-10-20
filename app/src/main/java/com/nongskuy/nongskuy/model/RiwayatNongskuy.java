package com.nongskuy.nongskuy.model;

public class RiwayatNongskuy {
    public String namaToko;
    public String statusPesan;
    public Integer totalKursi;
    public Integer totalDeposit;
    public String caraBayar;
    public String tglPesan;
    public String waktuPesan;

    public RiwayatNongskuy() {}

    public RiwayatNongskuy(String namaToko, String statusPesan, Integer totalKursi, Integer totalDeposit, String caraBayar, String tglPesan, String waktuPesan){
        this.namaToko = namaToko;
        this.statusPesan = statusPesan;
        this.totalKursi = totalKursi;
        this.totalDeposit = totalDeposit;
        this.caraBayar = caraBayar;
        this.tglPesan = tglPesan;
        this.waktuPesan = waktuPesan;
    }
}
