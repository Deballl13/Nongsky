package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class NongskuyData {

	@SerializedName("hari_ops")
	private String hariOps;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("web")
	private String web;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("id")
	private int id;

	@SerializedName("fasilitas")
	private String fasilitas;

	@SerializedName("tipe")
	private String tipe;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("ig")
	private String ig;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("longitude")
	private String longitude;

	public void setHariOps(String hariOps){
		this.hariOps = hariOps;
	}

	public String getHariOps(){
		return hariOps;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
	}

	public void setWeb(String web){
		this.web = web;
	}

	public String getWeb(){
		return web;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setFasilitas(String fasilitas){
		this.fasilitas = fasilitas;
	}

	public String getFasilitas(){
		return fasilitas;
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

	public void setIg(String ig){
		this.ig = ig;
	}

	public String getIg(){
		return ig;
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

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}
}