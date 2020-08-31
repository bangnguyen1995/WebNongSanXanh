package edu.poly.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="hoadonchitiet")
public class HoaDonChiTiet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maHDCT;
	@Column
	private int soluongmua;
	@Column
	private int giamgia;
	@ManyToOne
	@JoinColumn(name="maSP")
	private SanPham sanpham;
	@ManyToOne
	@JoinColumn(name = "maHD")
	private HoaDon hoadon;
	public Integer getMaHDCT() {
		return maHDCT;
	}
	public void setMaHDCT(Integer maHDCT) {
		this.maHDCT = maHDCT;
	}
	public int getSoluongmua() {
		return soluongmua;
	}
	public void setSoluongmua(int soluongmua) {
		this.soluongmua = soluongmua;
	}
	public int getGiamgia() {
		return giamgia;
	}
	public void setGiamgia(int giamgia) {
		this.giamgia = giamgia;
	}
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public HoaDon getHoadon() {
		return hoadon;
	}
	public void setHoadon(HoaDon hoadon) {
		this.hoadon = hoadon;
	}
	public HoaDonChiTiet(Integer maHDCT, int soluongmua, int giamgia, SanPham sanpham, HoaDon hoadon) {
		super();
		this.maHDCT = maHDCT;
		this.soluongmua = soluongmua;
		this.giamgia = giamgia;
		this.sanpham = sanpham;
		this.hoadon = hoadon;
	}
	public HoaDonChiTiet() {
		super();
	}
	
	
}
