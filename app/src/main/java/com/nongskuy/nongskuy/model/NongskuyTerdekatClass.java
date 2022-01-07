package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.NongskuyTerdekatData;

public class NongskuyTerdekatClass{

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("toko")
	private List<NongskuyTerdekatData> toko;

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setToko(List<NongskuyTerdekatData> toko){
		this.toko = toko;
	}

	public List<NongskuyTerdekatData> getToko(){
		return toko;
	}
}