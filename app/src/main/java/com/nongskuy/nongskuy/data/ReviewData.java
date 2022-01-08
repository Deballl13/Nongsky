package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class ReviewData {

	@SerializedName("nama")
	private String nama;

	@SerializedName("komentar")
	private String komentar;

	@SerializedName("rating")
	private String rating;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("gambar")
	private Object gambar;

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKomentar(String komentar){
		this.komentar = komentar;
	}

	public String getKomentar(){
		return komentar;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setGambar(Object gambar){
		this.gambar = gambar;
	}

	public Object getGambar(){
		return gambar;
	}
}