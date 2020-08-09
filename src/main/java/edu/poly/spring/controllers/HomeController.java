package edu.poly.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.LoaiSanPhamService;
import edu.poly.spring.services.SanPhamService;

@Controller
public class HomeController {
	@Autowired
	private LoaiSanPhamService loaisanphamService;

	@Autowired
	private SanPhamService sanphamService;

	@RequestMapping("/index")
	public String index() {
		return "views/index";
	}

	@RequestMapping("/chitiet")
	public String index2() {
		return "product/ChiTietSP";
	}

	// Tìm kiếm sản phẩm theo loại sp - index
	@GetMapping({ "/find/{malsp}/{page}", "/find/{malsp}" })
	public String findlsp(ModelMap model, @PathVariable("malsp") String malsp,
			@PathVariable(name = "page", required = false) Optional<Integer> pageOpt, HttpSession session) {

		int page = 0;

		if (pageOpt.isPresent()) {
			page = pageOpt.get();
		}
		Optional<LoaiSanPham> lsp = loaisanphamService.findById(malsp);
		Pageable pageable = PageRequest.of(page, 12);
		Page<SanPham> list = (Page<SanPham>) sanphamService.findSanPhamByMaLSP(malsp, pageable);
		model.addAttribute("dssptheoloai", list);
		model.addAttribute("maloaisp", malsp);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", list.getTotalPages());
		model.addAttribute("lsp", lsp.get());
		// session.setAttribute("nd", session.getAttribute("nd"));
		return "product/DSSPTheoLoai";
	}

	// Tìm kiếm sản phẩm theo tên sản phẩm - index
	@GetMapping("/timkiem")
	public String findbyname(ModelMap model, @RequestParam(defaultValue = "") String tensp,
			@RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<SanPham> list = (Page<SanPham>) sanphamService.findByTenSPLikeOrderByTenSP("%" + tensp + "%", pageable);
		if (list.getTotalElements() == 0) {
			model.addAttribute("total", list.getTotalElements());
			model.addAttribute("messager", "Không tìm thấy sản phẩm nào");
		}
		model.addAttribute("products", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("tensp", tensp);
		model.addAttribute("totalpage", list.getTotalPages());
		return "product/DSTatCaSanPham";
	}

	// Hiển thị danh sách sản phẩm - index
	@ModelAttribute("loaisanpham")
	List<LoaiSanPham> listLSP() {
		return loaisanphamService.findAll();
	}

	// Hiển thị top 10 sản phẩm khuyến mãi
	@ModelAttribute("top10km")
	List<SanPham> topSPkm() {
		return sanphamService.findTop10SanPhamByMaMK();
	}

	// Hiển thị top 12 sản phẩm bán chạy
	@ModelAttribute("top12sp")
	List<SanPham> top12sp() {
		return sanphamService.findTop12SanPhamBySUMSoLuong();
	}

	// Top 10 sản phẩm mới nhập
	@ModelAttribute("top10spmoinhap")
	List<SanPham> top6spMoiNhap() {
		return sanphamService.findTop10SanPhamOrderByNgayNhap();
	}

	// Top 10 sản phẩm đánh giá tốt
	@ModelAttribute("top10spdanhgia")
	List<SanPham> top10spDanhGia() {
		return sanphamService.findTop10SanPhamOrderByDanhGia();
	}

	// Hiển thị danh sách sản phẩm khuyến mãi
	@RequestMapping("/danhsachkhuyenmai")
	public String dskm(ModelMap model, @RequestParam(defaultValue = "0") int page, HttpSession session) {
		Pageable pageable = PageRequest.of(page, 12);
		Page<SanPham> list = (Page<SanPham>) sanphamService.findSanPhamByMaMK(pageable);
		model.addAttribute("dsspkhuyenmai", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", list.getTotalPages());
		return "product/DSSPKhuyenMai";
	}

}
