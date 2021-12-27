package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.TokoPopulerData;

public class TokoPopulerClass {
	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("toko_populer")
	private List<TokoPopulerData> tokoPopuler;

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

	public void setTokoPopuler(List<TokoPopulerData> tokoPopuler){
		this.tokoPopuler = tokoPopuler;
	}

	public List<TokoPopulerData> getTokoPopuler(){
		return tokoPopuler;
	}
}