package com.nongskuy.nongskuy.model;

public class Review {

    private Integer idReview;
    private Integer idUser;
    private Integer idToko;
    private String namaUser;
    private String komentar;
    private String tanggal;
    private String rating;
    private String gambar;

    //horizontal review RV
    public Review(Integer idReview, String namaUser, String komentar, String tanggal, String rating){
        this.idReview = idReview;
        this.namaUser = namaUser;
        this.komentar = komentar;
        this.tanggal = tanggal;
        this.rating = rating;
    }

    public Review(Integer idReview, Integer idUser, Integer idToko, String komentar, String tanggal, String rating){
        this.idReview = idReview;
        this.idUser = idUser;
        this.idToko = idToko;
        this.komentar = komentar;
        this.tanggal = tanggal;
        this.rating = rating;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdToko() {
        return idToko;
    }

    public void setIdToko(Integer idToko) {
        this.idToko = idToko;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }
}
