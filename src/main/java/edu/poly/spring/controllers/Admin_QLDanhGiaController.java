package edu.poly.spring.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.DanhGia;
import edu.poly.spring.services.DanhGiaService;

@Controller
@RequestMapping("/danhgia")
public class Admin_QLDanhGiaController {
	@Autowired
	private DanhGiaService danhgiaService;
	
	@GetMapping("/danhsach")
	private String listDanhGia(ModelMap model, @RequestParam(defaultValue = "0") int page, HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 5);
		Page<DanhGia> list = (Page<DanhGia>) danhgiaService.findAll(pageable);
		model.addAttribute("danhgia", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total",list.getTotalPages());
		return "admin/DanhSachDanhGia";
	}	
	
	@GetMapping("/xoa/{maDG}")
	private String xoaDanhGia(ModelMap model, @PathVariable(name="maDG") Integer maDG,
			@RequestParam(defaultValue = "0") int page) {
		danhgiaService.deleteById(maDG);
		return "redirect:/danhgia/danhsach";
	}
}
