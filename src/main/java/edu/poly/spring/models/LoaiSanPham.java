package edu.poly.spring.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder.In;
@Entity
@Table(name="loaisanpham")
public class LoaiSanPham {
	@Id
	@Column(length = 15)
	private String maLSP;
	@Column(length = 50)
	private String tenLSP;
	@Column
	private int loai;
	@OneToMany (mappedBy ="loaisanpham",cascade = CascadeType.ALL )
	private Collection <SanPham> sanpham;

	public LoaiSanPham() {
		super();
	}

	public LoaiSanPham(String maLSP, String tenLSP, int loai, Collection<SanPham> sanpham) {
		super();
		this.maLSP = maLSP;
		this.tenLSP = tenLSP;
		this.loai = loai;
		this.sanpham = sanpham;
	}

	public String getMaLSP() {
		return maLSP;
	}

	public void setMaLSP(String maLSP) {
		this.maLSP = maLSP;
	}

	public String getTenLSP() {
		return tenLSP;
	}

	public void setTenLSP(String tenLSP) {
		this.tenLSP = tenLSP;
	}

	public int getLoai() {
		return loai;
	}

	public void setLoai(int loai) {
		this.loai = loai;
	}

	public Collection<SanPham> getSanpham() {
		return sanpham;
	}

	public void setSanpham(Collection<SanPham> sanpham) {
		this.sanpham = sanpham;
	}

	

	

	
}
