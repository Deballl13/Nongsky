package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.PencarianData;

public class PencarianClass {
	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("search_result")
	private List<PencarianData> searchResult;

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

	public void setSearchResult(List<PencarianData> searchResult){
		this.searchResult = searchResult;
	}

	public List<PencarianData> getSearchResult(){
		return searchResult;
	}
}