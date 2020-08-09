package edu.poly.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.spring.models.HoaDon;
import edu.poly.spring.models.Item;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.NguoiDung;
import edu.poly.spring.models.PhuongThucThanhToan;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.LoaiSanPhamService;
import edu.poly.spring.services.PhuongThucThanhToanService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private SanPhamService sanphamservice;
	@Autowired
	private PhuongThucThanhToanService phuongthucthanhtoanservice;
	@Autowired
	private LoaiSanPhamService loaisanphamservice;
@GetMapping("/list")
public String list(ModelMap model, HttpSession session) {
	HoaDon hoadon = new HoaDon();
	model.addAttribute("hoadon", hoadon);
	List<Item> cart = (List<Item>) session.getAttribute("cart");	
	NguoiDung nguoidung   = (NguoiDung) session.getAttribute("user");
	if (nguoidung==null) {
		return "redirect:/login";
	}
	if(cart == null) {
		model.put("total", 0);
		return "product/cart";
	}
	model.put("total", total(session));
	

	return "product/cart";
}
	@GetMapping("/buy/{masp}")
	public String buy(ModelMap model, @PathVariable(name = "masp") Integer masp, HttpSession session) {
		
		if (session.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			Optional<SanPham> sanpham = sanphamservice.findById(masp);
			cart.add(new Item(1, sanpham.get()));
			session.setAttribute("cart", cart);
		} else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int index = isExists(masp, cart);
			if (index == -1) {
				Optional<SanPham> sanpham = sanphamservice.findById(masp);
				cart.add(new Item(1, sanpham.get()));
			} else {
				int quatity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quatity);
			}
			session.setAttribute("cart", cart);
		}
		return "redirect:/cart/list";
	}

	private int isExists(int id, List<Item> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getSanpham().getMaSP() == id) {
				return i;

			}
		}
		return -1;
	}

	@GetMapping("/remove/{masp}")
	public String remove(ModelMap model, @PathVariable(name = "masp") Integer masp, HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int index = isExists(masp, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/list";
	}
	@PostMapping("/update")
	public String update(ModelMap model, HttpSession session,HttpServletRequest request) {
		String [] quantities = request.getParameterValues("quantity");
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		if(cart==null) {
			return "redirect:/cart/list";
		}
		for(int i =0; i <cart.size();i++) {
		cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
	}
		session.setAttribute("cart", cart);
		return "redirect:/cart/list";
	}
	private double total (HttpSession session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		double s =0 ;
		for (Item item : cart) {
			s += (item.getQuantity()*item.getSanpham().getGiaSP())-(item.getQuantity()*item.getSanpham().getGiaSP()*item.getSanpham().getKhuyenmai().getGiamgia()/100);
		}
		
		return s;
	}
	@ModelAttribute(name = "listpttt")
	List<PhuongThucThanhToan> getpttt() {
		return phuongthucthanhtoanservice.findAll();
	}
	@ModelAttribute("loaisanpham")
	List<LoaiSanPham> listLSP() {
		return loaisanphamservice.findAll();
	}
}
