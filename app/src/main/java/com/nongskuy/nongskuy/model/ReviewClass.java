package com.nongskuy.nongskuy.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.nongskuy.nongskuy.data.ReviewData;

public class ReviewClass{

	@SerializedName("jumlah")
	private int jumlah;

	@SerializedName("review")
	private List<ReviewData> review;

	@SerializedName("tanggal")
	private String tanggal;

	public void setJumlah(int jumlah){
		this.jumlah = jumlah;
	}

	public int getJumlah(){
		return jumlah;
	}

	public void setReview(List<ReviewData> review){
		this.review = review;
	}

	public List<ReviewData> getReview(){
		return review;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}
}