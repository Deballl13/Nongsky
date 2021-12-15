package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RiwayatNongskuyClass{

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("riwayat")
	private List<RiwayatItem> riwayat;

	@SerializedName("tanggal")
	private String tanggal;

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setRiwayat(List<RiwayatItem> riwayat){
		this.riwayat = riwayat;
	}

	public List<RiwayatItem> getRiwayat(){
		return riwayat;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}