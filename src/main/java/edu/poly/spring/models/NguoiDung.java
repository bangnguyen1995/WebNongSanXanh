package edu.poly.spring.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="nguoidung")
public class NguoiDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maND;
	
	@NotNull
	@NotEmpty(message = "Hãy nhập họ và tên")
	@Column(length = 50)
    private String tenND;
	
	@NotNull
	@NotEmpty(message = "Hãy nhập email")
	@Column(length = 50)
	private String email;
	
	@NotNull
	@NotEmpty(message = "Hãy nhập địa chỉ")
	@Column(length = 50)
	private String diachi;
	
	@NotNull
	@NotEmpty(message = "Hãy nhập số điện thoại")
	@Column(length = 12)
	private String sdt;
	@Column
	private boolean trangthai;
	@NotNull
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngaysinh;
	
	@NotNull
	@NotEmpty(message = "Hãy nhập mật khẩu")
	@Column(length = 15)
	private String matkhau;

	@Column
	private boolean gioitinh;
	
	@OneToMany(mappedBy = "nguoidung", cascade = CascadeType.ALL)
	private Collection<DanhGia> danhgia;
	
	@OneToMany(mappedBy = "nguoidung", cascade = CascadeType.ALL)
	private Collection<HoaDon> hoadon;

	public NguoiDung(Integer maND, @NotNull @NotEmpty(message = "Hãy nhập họ và tên") String tenND,
			@NotNull @NotEmpty(message = "Hãy nhập email") String email,
			@NotNull @NotEmpty(message = "Hãy nhập địa chỉ") String diachi,
			@NotNull @NotEmpty(message = "Hãy nhập số điện thoại") String sdt, boolean trangthai, Date ngaysinh,
			@NotNull @NotEmpty(message = "Hãy nhập mật khẩu") String matkhau, boolean gioitinh,
			Collection<DanhGia> danhgia, Collection<HoaDon> hoadon) {
		super();
		this.maND = maND;
		this.tenND = tenND;
		this.email = email;
		this.diachi = diachi;
		this.sdt = sdt;
		this.trangthai = trangthai;
		this.ngaysinh = ngaysinh;
		this.matkhau = matkhau;
		this.gioitinh = gioitinh;
		this.danhgia = danhgia;
		this.hoadon = hoadon;
	}

	public NguoiDung() {
		super();
	}

	public Integer getMaND() {
		return maND;
	}

	public void setMaND(Integer maND) {
		this.maND = maND;
	}

	public String getTenND() {
		return tenND;
	}

	public void setTenND(String tenND) {
		this.tenND = tenND;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public boolean isTrangthai() {
		return trangthai;
	}

	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}

	public Date getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public boolean isGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}

	public Collection<DanhGia> getDanhgia() {
		return danhgia;
	}

	public void setDanhgia(Collection<DanhGia> danhgia) {
		this.danhgia = danhgia;
	}

	public Collection<HoaDon> getHoadon() {
		return hoadon;
	}

	public void setHoadon(Collection<HoaDon> hoadon) {
		this.hoadon = hoadon;
	}
	
	
	
	
	
	
	
}
