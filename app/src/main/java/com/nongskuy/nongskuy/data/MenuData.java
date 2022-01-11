package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class MenuData {

	@SerializedName("nama_menu")
	private String namaMenu;

	@SerializedName("harga")
	private int harga;

	@SerializedName("jenis_promo")
	private String jenisPromo;

	@SerializedName("id")
	private int id;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("status")
	private String status;

	@SerializedName("persentase")
	private int persentase;

	public void setNamaMenu(String namaMenu){
		this.namaMenu = namaMenu;
	}

	public String getNamaMenu(){
		return namaMenu;
	}

	public void setHarga(int harga){
		this.harga = harga;
	}

	public int getHarga(){
		return harga;
	}

	public void setJenisPromo(String jenisPromo){
		this.jenisPromo = jenisPromo;
	}

	public String getJenisPromo(){
		return jenisPromo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setPersentase(int persentase){
		this.persentase = persentase;
	}

	public int getPersentase(){
		return persentase;
	}
}