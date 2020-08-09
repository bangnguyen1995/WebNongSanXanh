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
	@ManyToOne
	@JoinColumn(name="maSP")
	private SanPham sanpham;
	@ManyToOne
	@JoinColumn(name = "maHD")
	private HoaDon hoadon;
	public HoaDonChiTiet(Integer maHDCT, int soluongmua, edu.poly.spring.models.SanPham sanpham,
			edu.poly.spring.models.HoaDon hoadon) {
		super();
		this.maHDCT = maHDCT;
		this.soluongmua = soluongmua;
		this.sanpham = sanpham;
		this.hoadon = hoadon;
	}
	public HoaDonChiTiet() {
		super();
	}
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
	
	
}
