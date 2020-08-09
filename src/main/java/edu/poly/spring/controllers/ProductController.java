package edu.poly.spring.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import edu.poly.spring.models.DanhGia;
import edu.poly.spring.models.HinhAnhSP;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.NguoiDung;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.DanhGiaService;
import edu.poly.spring.services.HinhAnhSPService;
import edu.poly.spring.services.LoaiSanPhamService;
import edu.poly.spring.services.NguoiDungService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/sanpham")
public class ProductController {
	@Autowired
	private SanPhamService sanphamservice;

	@Autowired
	private HinhAnhSPService hinhanhspService;

	@Autowired
	private LoaiSanPhamService loaisanphamService;
	
	@Autowired
	private NguoiDungService nguoidungService;
	
	@Autowired 
	private DanhGiaService danhgiaService;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@RequestMapping("/tatca")
	public String product(ModelMap model, @RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<SanPham> list = (Page<SanPham>) sanphamservice.findAll(pageable);
		model.addAttribute("products", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", list.getTotalPages());
		return "product/DSTatCaSanPham";
	}

	@RequestMapping("/chitiet/{masp}")
	public String detailProduct(ModelMap model, @PathVariable(name = "masp") Integer masp) {

		// Tìm kiếm sp
		Optional<SanPham> sanPham = sanphamservice.findById(masp);
		model.addAttribute("chitietsp", sanPham.get());

		// Tìm kiếm hình ảnh sp
		List<HinhAnhSP> hinhanhsp = hinhanhspService.findHinhAnhSanPhamByMaSP(masp);
		model.addAttribute("hinhanhchitietsp", hinhanhsp);

		String maloai = sanPham.get().getLoaisanpham().getMaLSP();
		List<SanPham> list = sanphamservice.findTop10SanPhamByMaLSP(maloai, masp);
		model.addAttribute("spLienQuan", list);
		
		DanhGia danhgia = new DanhGia();
		danhgia.setSanpham(sanphamservice.findById(masp).get());	
		model.addAttribute("danhsachdanhgia", danhgia);
		
		//Hiển thị tổng đánh giá và số sao trung bình
		Session sess = entityManagerFactory.createEntityManager().unwrap(Session.class);
		String hql = "Select AVG(dg.rating) From DanhGia dg where masp = " + masp;
		Double sosaoavg = (Double) sess.createQuery(hql).getSingleResult();
		String hql2 = "Select COUNT(dg.maDG) From DanhGia dg where masp = " + masp;
		Long countdanhgia = (Long) sess.createQuery(hql2).getSingleResult();
		model.addAttribute("sosaoavg", sosaoavg);
		model.addAttribute("countdanhgia", countdanhgia);
		return "product/ChiTietSP";
	}

	// Hiển thị danh sách sản phẩm - index
		@ModelAttribute("loaisanpham")
		List<LoaiSanPham> listLSP() {
			return loaisanphamService.findAll();
		}
	
	//Đánh giá sản phẩm
	@PostMapping("/danhgia")
	public String danhgiasp(ModelMap model,
			@ModelAttribute("danhgia") DanhGia danhgia,
			@RequestParam("hdrating") float hdrating,
			HttpSession session) {
		NguoiDung nd = (NguoiDung) session.getAttribute("user");
		String email = nd.getEmail();
		danhgia.setNguoidung(nguoidungService.findByEmail(email));
		danhgia.setNgaydang(new Date());
		danhgia.setRating(hdrating);
		danhgiaService.save(danhgia);
		return "redirect:/sanpham/chitiet/" + danhgia.getSanpham().getMaSP();
	}
}
