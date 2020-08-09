package edu.poly.spring.Dto;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
public class KhuyenMaiDto implements Serializable {
    @NotNull
    @NotEmpty
	private String maKM;
    @NotNull
    @NotEmpty
    private String tenKM;
    @NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaybatdau;
    @NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayketthuc;
    @NotNull
    private int giamgia;
    @NotNull
    @NotEmpty
    private String chitietKM;

	public String getMaKM() {
		return maKM;
	}
	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}
	public String getTenKM() {
		return tenKM;
	}
	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	public int getGiamgia() {
		return giamgia;
	}
	public void setGiamgia(int giamgia) {
		this.giamgia = giamgia;
	}
	public String getChitietKM() {
		return chitietKM;
	}
	public void setChitietKM(String chitietKM) {
		this.chitietKM = chitietKM;
	}
	
   
	
}
