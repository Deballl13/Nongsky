package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class PromoData {

	@SerializedName("id_menu")
	private int idMenu;

	@SerializedName("nama_menu")
	private String namaMenu;

	@SerializedName("harga")
	private int harga;

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("jenis_promo")
	private String jenisPromo;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("persentase")
	private int persentase;

	public void setIdMenu(int idMenu){
		this.idMenu = idMenu;
	}

	public int getIdMenu(){
		return idMenu;
	}

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

	public void setIdToko(int idToko){
		this.idToko = idToko;
	}

	public int getIdToko(){
		return idToko;
	}

	public void setJenisPromo(String jenisPromo){
		this.jenisPromo = jenisPromo;
	}

	public String getJenisPromo(){
		return jenisPromo;
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

	public void setPersentase(int persentase){
		this.persentase = persentase;
	}

	public int getPersentase(){
		return persentase;
	}
}