package edu.poly.spring.controllers;


import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.Dto.LoaiSanPhamDto;

import edu.poly.spring.models.LoaiSanPham;

import edu.poly.spring.services.LoaiSanPhamService;

@Controller
@RequestMapping("/loaisanpham")
public class AdminLoaiSanPhamController {
	@Autowired
	private LoaiSanPhamService loaisanphamservice;

	@GetMapping("/listlsp")
	public String listloaisanpham(ModelMap model, @RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 5);
		Page<LoaiSanPham> list = (Page<LoaiSanPham>) loaisanphamservice.findAll(pageable);
		model.addAttribute("list", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", list.getTotalPages());
		return "admin/DanhsachLoaiSP";
	}

	@GetMapping("/deletelsp/{malsp}")
	public String deletelsp(ModelMap model, @PathVariable(name = "malsp") String malsp,
			@RequestParam(defaultValue = "0") Integer page) {
		loaisanphamservice.deleteById(malsp);

		return "redirect:/loaisanpham/listlsp";
	}

	@GetMapping("/findbylsp/{malsp}")
	public String findbylsp(ModelMap model, @PathVariable(name = "malsp") String malsp,
			@RequestParam(defaultValue = "0") Integer page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Optional<LoaiSanPham> lsp = loaisanphamservice.findById(malsp);
		if (lsp.isPresent()) {
			LoaiSanPhamDto lspdto = new LoaiSanPhamDto();
			lspdto.setMaLSP(lsp.get().getMaLSP());
			lspdto.setTenLSP(lsp.get().getTenLSP());
			lspdto.setLoai(lsp.get().getLoai());
			model.addAttribute("lspdto",lspdto);
			
		}
		return "admin/SuaLSP";
	}

	@GetMapping("/findbytenlsp")
	public String findbytenlsp(ModelMap model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "") String tenlsp,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 5);
		Page<LoaiSanPham> list = (Page<LoaiSanPham>) loaisanphamservice
				.findByTenLSPLikeOrderByTenLSP("%" + tenlsp + "%", pageable);
		if (list.getTotalElements() == 0) {
			model.addAttribute("total", list.getTotalElements());
			model.addAttribute("messager", "Không tìm thấy loại sản phẩm nào");
		}
		model.addAttribute("list", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("tenlsp", tenlsp);
		model.addAttribute("totalpage", list.getTotalPages());
		return "admin/DanhsachLoaiSP";
	}

	@GetMapping("/addlsp")
	public String addlsp(ModelMap model,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
	model.addAttribute("lspdto",new LoaiSanPhamDto());
		return "admin/ThemLSP2";
	}

	@PostMapping("/savelsp")
	public String savelsp(ModelMap model, @Validated LoaiSanPhamDto lspdto, BindingResult rs) {
		Optional<LoaiSanPham> lsp1 =  loaisanphamservice.findById(lspdto.getMaLSP());
		if (rs.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("lspdto", lspdto);
			System.out.println("lỗi chưa nhập");
			
			return "admin/ThemLSP2";
		}
		if (lspdto.getLoai() <=0 || lspdto.getLoai()>3) {
			model.addAttribute("message", "Loại phải lớn hơn 0  và nhỏ hơn 4");
			model.addAttribute("lspdto", lspdto);
			return "admin/ThemLSP2";
		}
		if(lsp1.isPresent()) {
			model.addAttribute("lspdto", lspdto);
			model.addAttribute("message","Mã loại sản phẩm đã tồn tại");
			return "admin/ThemLSP2";
		}
		String message = "Thêm mới thành công !";
		if (lspdto.getMaLSP().length() > 0) {
			message = "Lưu thành không !";
		}

		LoaiSanPham lsp = new LoaiSanPham();
		lsp.setMaLSP(lspdto.getMaLSP());
		lsp.setTenLSP(lspdto.getTenLSP());
		lsp.setLoai(lspdto.getLoai());
		loaisanphamservice.save(lsp);
		model.addAttribute("lspdto", lspdto);
		model.addAttribute("message", message);
		return "admin/ThemLSP2";
	}
	@PostMapping("/editlsp")
	public String editlsp(ModelMap model, @Validated LoaiSanPhamDto lspdto, BindingResult rs) {
	
		if (rs.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("lspdto", lspdto);
			System.out.println("lỗi chưa nhập");
			
			return "admin/SuaLSP";
		}
		if (lspdto.getLoai() <=0 || lspdto.getLoai()>3) {
			model.addAttribute("message", "Loại phải lớn hơn 0  và nhỏ hơn 4");
			model.addAttribute("lspdto", lspdto);
			return "admin/SuaLSP";
		}
		String message = "Thêm mới thành công !";
		if (lspdto.getMaLSP().length() > 0) {
			message = "Lưu thành không !";
		}

		LoaiSanPham lsp = new LoaiSanPham();
		lsp.setMaLSP(lspdto.getMaLSP());
		lsp.setTenLSP(lspdto.getTenLSP());
		lsp.setLoai(lspdto.getLoai());
		loaisanphamservice.save(lsp);
		model.addAttribute("lspdto", lspdto);
		model.addAttribute("message", message);
		return "admin/SuaLSP";
	}
}
