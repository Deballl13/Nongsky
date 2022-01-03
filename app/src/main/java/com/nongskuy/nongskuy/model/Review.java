package com.nongskuy.nongskuy.model;

public class Review {

    private Integer idReview;
    private Integer idUser;
    private Integer idToko;
    private String komentar;
    private String tanggal;
    private Double rating;
    private String gambar;

    public Review(Integer idReview, Integer idUser, Integer idToko, String komentar, String tanggal, Double rating){
        this.idReview = idReview;
        this.idUser = idUser;
        this.idToko = idToko;
        this.komentar = komentar;
        this.tanggal = tanggal;
        this.rating = rating;
        //this.gambar = gambar;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
