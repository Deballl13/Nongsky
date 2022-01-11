package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.NongskuyData;

public class NongskuyClass{

	@SerializedName("toko")
	private List<NongskuyData> toko;

	@SerializedName("tanggal")
	private String tanggal;

	public void setToko(List<NongskuyData> toko){
		this.toko = toko;
	}

	public List<NongskuyData> getToko(){
		return toko;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}