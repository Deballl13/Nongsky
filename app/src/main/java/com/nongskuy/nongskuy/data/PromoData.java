package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class PromoData {

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("gambar_toko")
	private String gambarToko;

	@SerializedName("alamat_toko")
	private String alamatToko;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("longitude")
	private String longitude;

	@SerializedName("id_menu")
	private int idMenu;

	@SerializedName("nama_menu")
	private String namaMenu;

	@SerializedName("harga")
	private int harga;

	@SerializedName("gambar_menu")
	private String gambarMenu;

	@SerializedName("jenis_promo")
	private String jenisPromo;

	@SerializedName("persentase")
	private int persentase;

	public int getIdToko() {
		return idToko;
	}

	public void setIdToko(int idToko) {
		this.idToko = idToko;
	}

	public String getNamaToko() {
		return namaToko;
	}

	public void setNamaToko(String namaToko) {
		this.namaToko = namaToko;
	}

	public String getGambarToko() {
		return gambarToko;
	}

	public void setGambarToko(String gambarToko) {
		this.gambarToko = gambarToko;
	}

	public String getAlamatToko() {
		return alamatToko;
	}

	public void setAlamatToko(String alamatToko) {
		this.alamatToko = alamatToko;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getNamaMenu() {
		return namaMenu;
	}

	public void setNamaMenu(String namaMenu) {
		this.namaMenu = namaMenu;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}

	public String getGambarMenu() {
		return gambarMenu;
	}

	public void setGambarMenu(String gambarMenu) {
		this.gambarMenu = gambarMenu;
	}

	public String getJenisPromo() {
		return jenisPromo;
	}

	public void setJenisPromo(String jenisPromo) {
		this.jenisPromo = jenisPromo;
	}

	public int getPersentase() {
		return persentase;
	}

	public void setPersentase(int persentase) {
		this.persentase = persentase;
	}
}