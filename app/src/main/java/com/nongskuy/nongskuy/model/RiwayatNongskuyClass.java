package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.RiwayatNongskuyData;

public class RiwayatNongskuyClass{

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("riwayat")
	private List<RiwayatNongskuyData> riwayat;

	@SerializedName("tanggal")
	private String tanggal;

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setRiwayat(List<RiwayatNongskuyData> riwayat){
		this.riwayat = riwayat;
	}

	public List<RiwayatNongskuyData> getRiwayat(){
		return riwayat;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}