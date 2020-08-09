package edu.poly.spring.Dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoaiSanPhamDto implements Serializable {
	@NotNull
	@NotEmpty
	private String maLSP;
	@NotNull
	@NotEmpty
	private String tenLSP;
	@NotNull
	private int loai;
	public String getMaLSP() {
		return maLSP;
	}
	public void setMaLSP(String maLSP) {
		this.maLSP = maLSP;
	}
	public String getTenLSP() {
		return tenLSP;
	}
	public void setTenLSP(String tenLSP) {
		this.tenLSP = tenLSP;
	}
	public int getLoai() {
		return loai;
	}
	public void setLoai(int loai) {
		this.loai = loai;
	}
	

}
