package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.MetodeBayarNongskuyData;

public class MetodeBayarNongskuyClass{

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("metode_bayar")
	private List<MetodeBayarNongskuyData> metodeBayar;

	public int getJumlah(){
		return jumlah;
	}

	public String getTanggal(){
		return tanggal;
	}

	public List<MetodeBayarNongskuyData> getMetodeBayar(){
		return metodeBayar;
	}
}