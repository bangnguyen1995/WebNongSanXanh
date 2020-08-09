package edu.poly.spring.models;

import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="phuongthucthanhtoan")
public class PhuongThucThanhToan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maPT;
	@Column(length = 50)
	private String tenPT;
	@Column(length = 255)
	private String mota;
	@OneToMany(mappedBy = "phuongthucthanhtoan", cascade = CascadeType.ALL)
	private Collection<HoaDon> hoadon;
	public PhuongThucThanhToan(Integer maPT, String tenPT, String mota,
			Collection<edu.poly.spring.models.HoaDon> hoadon) {
		super();
		this.maPT = maPT;
		this.tenPT = tenPT;
		this.mota = mota;
		this.hoadon = hoadon;
	}
	public PhuongThucThanhToan() {
		super();
	}
	public Integer getMaPT() {
		return maPT;
	}
	public void setMaPT(Integer maPT) {
		this.maPT = maPT;
	}
	public String getTenPT() {
		return tenPT;
	}
	public void setTenPT(String tenPT) {
		this.tenPT = tenPT;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public Collection<HoaDon> getHoadon() {
		return hoadon;
	}
	public void setHoadon(Collection<HoaDon> hoadon) {
		this.hoadon = hoadon;
	}
	
	
}
