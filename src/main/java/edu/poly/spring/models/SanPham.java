package edu.poly.spring.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "sanpham")
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maSP;
	@Column(length = 50)
	private String tenSP;
	@Column
	private double giaSP;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaynhap;
	@Column(length = 50)
	private String mota;
	@Column(length = 50)
	private String anhSP;
	@Column
	private int soluong;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayhethan;
	@Column
	private boolean trangthai;
	@OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
	private Collection<HoaDonChiTiet> hoadonchitiet;
	@OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
	private Collection<DanhGia> danhgia;
	@OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
	private Collection<HinhAnhSP> hinhanhSP;
	@ManyToOne
	@JoinColumn(name = "maLSP")
	private LoaiSanPham loaisanpham;
	@ManyToOne
	@JoinColumn(name = "maKM")
	private KhuyenMai khuyenmai;
	

	public SanPham() {
		super();
	}


	public SanPham(Integer maSP, String tenSP, double giaSP, Date ngaynhap, String mota, String anhSP, int soluong,
			Date ngayhethan, boolean trangthai, Collection<HoaDonChiTiet> hoadonchitiet, Collection<DanhGia> danhgia,
			Collection<HinhAnhSP> hinhanhSP, LoaiSanPham loaisanpham, KhuyenMai khuyenmai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaSP = giaSP;
		this.ngaynhap = ngaynhap;
		this.mota = mota;
		this.anhSP = anhSP;
		this.soluong = soluong;
		this.ngayhethan = ngayhethan;
		this.trangthai = trangthai;
		this.hoadonchitiet = hoadonchitiet;
		this.danhgia = danhgia;
		this.hinhanhSP = hinhanhSP;
		this.loaisanpham = loaisanpham;
		this.khuyenmai = khuyenmai;
	}


	public Integer getMaSP() {
		return maSP;
	}


	public void setMaSP(Integer maSP) {
		this.maSP = maSP;
	}


	public String getTenSP() {
		return tenSP;
	}


	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}


	public double getGiaSP() {
		return giaSP;
	}


	public void setGiaSP(double giaSP) {
		this.giaSP = giaSP;
	}


	public Date getNgaynhap() {
		return ngaynhap;
	}


	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}


	public String getMota() {
		return mota;
	}


	public void setMota(String mota) {
		this.mota = mota;
	}


	public String getAnhSP() {
		return anhSP;
	}


	public void setAnhSP(String anhSP) {
		this.anhSP = anhSP;
	}


	public int getSoluong() {
		return soluong;
	}


	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}


	public Date getNgayhethan() {
		return ngayhethan;
	}


	public void setNgayhethan(Date ngayhethan) {
		this.ngayhethan = ngayhethan;
	}


	public boolean isTrangthai() {
		return trangthai;
	}


	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}


	public Collection<HoaDonChiTiet> getHoadonchitiet() {
		return hoadonchitiet;
	}


	public void setHoadonchitiet(Collection<HoaDonChiTiet> hoadonchitiet) {
		this.hoadonchitiet = hoadonchitiet;
	}


	public Collection<DanhGia> getDanhgia() {
		return danhgia;
	}


	public void setDanhgia(Collection<DanhGia> danhgia) {
		this.danhgia = danhgia;
	}


	public Collection<HinhAnhSP> getHinhanhSP() {
		return hinhanhSP;
	}


	public void setHinhanhSP(Collection<HinhAnhSP> hinhanhSP) {
		this.hinhanhSP = hinhanhSP;
	}


	public LoaiSanPham getLoaisanpham() {
		return loaisanpham;
	}


	public void setLoaisanpham(LoaiSanPham loaisanpham) {
		this.loaisanpham = loaisanpham;
	}


	public KhuyenMai getKhuyenmai() {
		return khuyenmai;
	}


	public void setKhuyenmai(KhuyenMai khuyenmai) {
		this.khuyenmai = khuyenmai;
	}


	

	

	

	

}
