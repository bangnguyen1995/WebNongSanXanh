package edu.poly.spring.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "khuyenmai")
public class KhuyenMai {
	@Id
	@Column(length = 15)
	private String maKM;
	@Column(length = 50)
	private String tenKM;
	@Column(length = 255)
	private String chitietKM;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngaybatdau;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date ngayketthuc;
	@Column
	private int giamgia;
	@Column
	private boolean trangthai = true;
	@OneToMany(mappedBy = "khuyenmai", cascade = CascadeType.ALL)
	private Collection<SanPham> sanpham;
	public KhuyenMai(String maKM, String tenKM, String chitietKM, Date ngaybatdau, Date ngayketthuc, int giamgia,
			boolean trangthai, Collection<SanPham> sanpham) {
		super();
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.chitietKM = chitietKM;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
		this.giamgia = giamgia;
		this.trangthai = trangthai;
		this.sanpham = sanpham;
	}
	public KhuyenMai() {
		super();
	}
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
	public String getChitietKM() {
		return chitietKM;
	}
	public void setChitietKM(String chitietKM) {
		this.chitietKM = chitietKM;
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
	public boolean isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}
	public Collection<SanPham> getSanpham() {
		return sanpham;
	}
	public void setSanpham(Collection<SanPham> sanpham) {
		this.sanpham = sanpham;
	}

	
	

}
