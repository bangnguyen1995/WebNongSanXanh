package edu.poly.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.services.LoaiSanPhamService;

@Controller

public class LienHeController {
	@Autowired
	private LoaiSanPhamService loaisanphamService;

	@GetMapping("/lienhe")
	public String lienhe() {
		return "views/contact";
	}

	@ModelAttribute("loaisanpham")
	List<LoaiSanPham> listLSP() {
		return loaisanphamService.findAll();
	}
}
