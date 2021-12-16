package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class RiwayatNongskuyData {

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("waktu")
	private String waktu;

	@SerializedName("jumlah_kursi")
	private int jumlahKursi;

	@SerializedName("gambar_toko")
	private String gambarToko;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("metode_bayar")
	private String metodeBayar;

	@SerializedName("dp")
	private int dp;

	@SerializedName("no_faktur")
	private int noFaktur;

	@SerializedName("nama_toko")
	private String namaToko;

	@SerializedName("status")
	private int status;

	public void setIdToko(int idToko){
		this.idToko = idToko;
	}

	public int getIdToko(){
		return idToko;
	}

	public void setWaktu(String waktu){
		this.waktu = waktu;
	}

	public String getWaktu(){
		return waktu;
	}

	public void setJumlahKursi(int jumlahKursi){
		this.jumlahKursi = jumlahKursi;
	}

	public int getJumlahKursi(){
		return jumlahKursi;
	}

	public void setGambarToko(String gambarToko){
		this.gambarToko = gambarToko;
	}

	public String getGambarToko(){
		return gambarToko;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setMetodeBayar(String metodeBayar){
		this.metodeBayar = metodeBayar;
	}

	public String getMetodeBayar(){
		return metodeBayar;
	}

	public void setDp(int dp){
		this.dp = dp;
	}

	public int getDp(){
		return dp;
	}

	public void setNoFaktur(int noFaktur){
		this.noFaktur = noFaktur;
	}

	public int getNoFaktur(){
		return noFaktur;
	}

	public void setNamaToko(String namaToko){
		this.namaToko = namaToko;
	}

	public String getNamaToko(){
		return namaToko;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}