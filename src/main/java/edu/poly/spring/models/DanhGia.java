package edu.poly.spring.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "danhgia")
public class DanhGia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maDG;
	@Column(length = 255)
	private String noidung;
	@Column()
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaydang;
	@Column()
	private float rating;
	@ManyToOne
	@JoinColumn(name = "maSP")
	private SanPham sanpham;
	@ManyToOne
	@JoinColumn(name = "maND")
	private NguoiDung nguoidung;
	
	public DanhGia(Integer maDG, String noidung, Date ngaydang, float rating, SanPham sanpham, NguoiDung nguoidung) {
		super();
		this.maDG = maDG;
		this.noidung = noidung;
		this.ngaydang = ngaydang;
		this.rating = rating;
		this.sanpham = sanpham;
		this.nguoidung = nguoidung;
	}

	public DanhGia() {
		super();
	}

	public Date getNgaydang() {
		return ngaydang;
	}

	public void setNgaydang(Date ngaydang) {
		this.ngaydang = ngaydang;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Integer getMaDG() {
		return maDG;
	}
	public void setMaDG(Integer maDG) {
		this.maDG = maDG;
	}
	public String getNoidung() {
		return noidung;
	}
	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public NguoiDung getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}

}
