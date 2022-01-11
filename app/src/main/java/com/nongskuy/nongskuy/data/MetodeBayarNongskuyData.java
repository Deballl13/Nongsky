package com.nongskuy.nongskuy.data;

import com.google.gson.annotations.SerializedName;

public class MetodeBayarNongskuyData {

	@SerializedName("nama_metode_pembayaran")
	private String namaMetodePembayaran;

	@SerializedName("no_rek")
	private String noRek;

	@SerializedName("id")
	private int id;

	public String getNamaMetodePembayaran(){
		return namaMetodePembayaran;
	}

	public String getNoRek(){
		return noRek;
	}

	public int getId(){
		return id;
	}
}