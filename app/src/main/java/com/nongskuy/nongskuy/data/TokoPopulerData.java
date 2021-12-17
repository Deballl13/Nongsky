package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class TokoPopulerData {

	@SerializedName("rating")
	private String rating;

	@SerializedName("id")
	private int id;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("alamat")
	private String alamat;

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

	public void setTipe(String tipe){
		this.tipe = tipe;
	}

	public String getTipe(){
		return tipe;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}