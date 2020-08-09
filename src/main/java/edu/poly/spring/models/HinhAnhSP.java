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
@Table(name = "hinhanhSP")
public class HinhAnhSP {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer maIMG;
	@Column(length = 25)
	private String anhSP;
	@ManyToOne
	@JoinColumn(name = "maSP")
	private SanPham sanpham;

	public HinhAnhSP() {
		super();
	}

	public HinhAnhSP(Integer maIMG, String anhSP, edu.poly.spring.models.SanPham sanpham) {
		super();
		this.maIMG = maIMG;
		this.anhSP = anhSP;
		this.sanpham = sanpham;
	}

	public Integer getMaIMG() {
		return maIMG;
	}

	public void setMaIMG(Integer maIMG) {
		this.maIMG = maIMG;
	}

	public String getAnhSP() {
		return anhSP;
	}

	public void setAnhSP(String anhSP) {
		this.anhSP = anhSP;
	}

	public SanPham getSanpham() {
		return sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

}
