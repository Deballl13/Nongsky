package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.PromoData;

public class PromoClass {

	@SerializedName("promo")
	private List<PromoData> promo;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("tanggal")
	private String tanggal;

	public void setPromo(List<PromoData> promo){
		this.promo = promo;
	}

	public List<PromoData> getPromo(){
		return promo;
	}

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}