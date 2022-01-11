package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.MenuData;

public class MenuClass{

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("menu")
	private List<MenuData> menu;

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

	public void setMenu(List<MenuData> menu){
		this.menu = menu;
	}

	public List<MenuData> getMenu(){
		return menu;
	}
}