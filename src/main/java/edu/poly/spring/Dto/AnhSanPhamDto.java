package edu.poly.spring.Dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class AnhSanPhamDto implements Serializable {
	
	Integer maIMG;
	@NotNull
	Integer maSP;
	@NotNull
	MultipartFile anh;
	public Integer getMaIMG() {
		return maIMG;
	}
	public void setMaIMG(Integer maIMG) {
		this.maIMG = maIMG;
	}
	public Integer getMaSP() {
		return maSP;
	}
	public void setMaSP(Integer maSP) {
		this.maSP = maSP;
	}
	public MultipartFile getAnh() {
		return anh;
	}
	public void setAnh(MultipartFile anh) {
		this.anh = anh;
	}
	
}
