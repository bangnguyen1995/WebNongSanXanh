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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "hoadon")
public class HoaDon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maHD;
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaydat;
	@Column
	private boolean trangthai;
	@Column
	private double tongtien;
	@Column(length = 255)
	@NotNull
	@NotEmpty
	private String diachinhan;
	@NotNull
	@NotEmpty
	@Column(length = 12)
	private String sdtNN;
	@Column(length = 50)
	private String nguoinhan;
	@ManyToOne
	@JoinColumn(name = "maND")
	private NguoiDung nguoidung;
	@ManyToOne
	@JoinColumn(name = "maPT")
	private PhuongThucThanhToan phuongthucthanhtoan;
	@OneToMany(mappedBy = "hoadon", cascade = CascadeType.ALL)
	private Collection<HoaDonChiTiet> hoadonchitiet;
	public HoaDon(Integer maHD, Date ngaydat, boolean trangthai, double tongtien, @NotNull @NotEmpty String diachinhan,
			@NotNull @NotEmpty String sdtNN, String nguoinhan, NguoiDung nguoidung,
			PhuongThucThanhToan phuongthucthanhtoan, Collection<HoaDonChiTiet> hoadonchitiet) {
		super();
		this.maHD = maHD;
		this.ngaydat = ngaydat;
		this.trangthai = trangthai;
		this.tongtien = tongtien;
		this.diachinhan = diachinhan;
		this.sdtNN = sdtNN;
		this.nguoinhan = nguoinhan;
		this.nguoidung = nguoidung;
		this.phuongthucthanhtoan = phuongthucthanhtoan;
		this.hoadonchitiet = hoadonchitiet;
	}
	public HoaDon() {
		super();
	}
	public Integer getMaHD() {
		return maHD;
	}
	public void setMaHD(Integer maHD) {
		this.maHD = maHD;
	}
	public Date getNgaydat() {
		return ngaydat;
	}
	public void setNgaydat(Date ngaydat) {
		this.ngaydat = ngaydat;
	}
	public boolean isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}
	public double getTongtien() {
		return tongtien;
	}
	public void setTongtien(double tongtien) {
		this.tongtien = tongtien;
	}
	public String getDiachinhan() {
		return diachinhan;
	}
	public void setDiachinhan(String diachinhan) {
		this.diachinhan = diachinhan;
	}
	public String getSdtNN() {
		return sdtNN;
	}
	public void setSdtNN(String sdtNN) {
		this.sdtNN = sdtNN;
	}
	public String getNguoinhan() {
		return nguoinhan;
	}
	public void setNguoinhan(String nguoinhan) {
		this.nguoinhan = nguoinhan;
	}
	public NguoiDung getNguoidung() {
		return nguoidung;
	}
	public void setNguoidung(NguoiDung nguoidung) {
		this.nguoidung = nguoidung;
	}
	public PhuongThucThanhToan getPhuongthucthanhtoan() {
		return phuongthucthanhtoan;
	}
	public void setPhuongthucthanhtoan(PhuongThucThanhToan phuongthucthanhtoan) {
		this.phuongthucthanhtoan = phuongthucthanhtoan;
	}
	public Collection<HoaDonChiTiet> getHoadonchitiet() {
		return hoadonchitiet;
	}
	public void setHoadonchitiet(Collection<HoaDonChiTiet> hoadonchitiet) {
		this.hoadonchitiet = hoadonchitiet;
	}

	
}
