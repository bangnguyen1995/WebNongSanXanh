package edu.poly.spring.controllers;

import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.HoaDon;
import edu.poly.spring.models.HoaDonChiTiet;
import edu.poly.spring.models.Item;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.NguoiDung;
import edu.poly.spring.models.PhuongThucThanhToan;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.HoaDonChiTietService;
import edu.poly.spring.services.HoaDonService;
import edu.poly.spring.services.LoaiSanPhamService;
import edu.poly.spring.services.PhuongThucThanhToanService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/oder")
public class OderController {
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	private HoaDonChiTietService hoadonchitietservice;
	@Autowired
	private PhuongThucThanhToanService phuongthucthanhtoanservice;
	@Autowired
	private SanPhamService sanphamservice;
	@Autowired
	private LoaiSanPhamService loaisanphamservice;
	
	@PostMapping("/saveoder")
	public String saveoder(ModelMap model, HttpSession session, @Validated HoaDon hoadon, BindingResult rs) {
		List<Item> list = (List<Item>) session.getAttribute("cart");
		if (rs.hasErrors()) {
			model.addAttribute("message", "Mua hàng không thành công.Bạn phải nhập đủ thông tin!");
			model.addAttribute("hoadon", hoadon);	
			double total = 0;
			for (Item item : list) {
				total += (item.getQuantity() * item.getSanpham().getGiaSP()) - (item.getQuantity()
						* item.getSanpham().getGiaSP() * item.getSanpham().getKhuyenmai().getGiamgia() / 100);

			}
			model.put("total", total);
			if (list == null) {
				model.put("total", 0);
			}
			return "product/cart";
		}
		String sdt = "0\\d{9}";
		if (!hoadon.getSdtNN().matches(sdt)) {
			model.addAttribute("message", "sai số điện thoại!");
			model.addAttribute("hoadon", hoadon);	
			double total = 0;
			for (Item item : list) {
				total += (item.getQuantity() * item.getSanpham().getGiaSP()) - (item.getQuantity()
						* item.getSanpham().getGiaSP() * item.getSanpham().getKhuyenmai().getGiamgia() / 100);

			}
			model.put("total", total);
			if (list == null) {
				model.put("total", 0);
			}
			return "product/cart";
		}
		NguoiDung nguoidung = (NguoiDung) session.getAttribute("user");
		hoadon.setNguoidung(nguoidung);
		hoadon.setNgaydat(new Date());
		hoadon.setTrangthai(false);
		double total = 0;
		for (Item item : list) {
			total += (item.getQuantity() * item.getSanpham().getGiaSP()) - (item.getQuantity()
					* item.getSanpham().getGiaSP() * item.getSanpham().getKhuyenmai().getGiamgia() / 100);
		}
		hoadon.setTongtien(total);
		hoadonservice.save(hoadon);

		for (Item item : list) {
			HoaDonChiTiet hoadonchitiet = new HoaDonChiTiet();
			hoadonchitiet.setHoadon(hoadon);
			hoadonchitiet.setSanpham(item.getSanpham());
			hoadonchitiet.setGiamgia(item.getSanpham().getKhuyenmai().getGiamgia());
			hoadonchitiet.setSoluongmua(item.getQuantity());
			hoadonchitietservice.save(hoadonchitiet);
			Optional<SanPham> sanpham = sanphamservice.findById(item.getSanpham().getMaSP());
			SanPham sp = new SanPham();
			sp.setMaSP(sanpham.get().getMaSP());
			sp.setAnhSP(sanpham.get().getAnhSP());
			sp.setGiaSP(sanpham.get().getGiaSP());
			sp.setKhuyenmai(sanpham.get().getKhuyenmai());
			sp.setLoaisanpham(sanpham.get().getLoaisanpham());
			sp.setMota(sanpham.get().getMota());
			sp.setTenSP(sanpham.get().getTenSP());
			sp.setNgayhethan(sanpham.get().getNgayhethan());
			sp.setNgaynhap(sanpham.get().getNgaynhap());
			sp.setTrangthai(sanpham.get().isTrangthai());
			sp.setSoluong(sanpham.get().getSoluong() - item.getQuantity());
			sanphamservice.save(sp);
		}
		session.removeAttribute("cart");
		return "redirect:/oder/findhoadonbynd";

	}

	@ModelAttribute(name = "listpttt")
	List<PhuongThucThanhToan> getpttt() {
		return phuongthucthanhtoanservice.findAll();
	}

	@GetMapping("/findhoadonbynd")
	public String findhoadonbynd(ModelMap model, HttpSession session, @RequestParam(defaultValue = "0") int page) {
		NguoiDung ngioidung = (NguoiDung) session.getAttribute("user");
		if (ngioidung == null) {
			return "redirect:/login";
		}
		Pageable pageable = PageRequest.of(page, 10);
		Page<HoaDon> hoadon = (Page<HoaDon>) hoadonservice.findByNguoidungMaNDOrderByNgaydatDesc(ngioidung.getMaND(),
				pageable);
		model.addAttribute("hoadon", hoadon);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", hoadon.getTotalPages());
		return "product/DanhSachDonHang";
	}
	@GetMapping("/chitiethd/{mahd}")
	public String chitiethd(ModelMap model, @PathVariable(name = "mahd") Integer mahd) {
		List<HoaDonChiTiet> listhdct = hoadonchitietservice.findByHoadonMaHD(mahd);
		model.addAttribute("listhdct", listhdct);
	
		return "product/ChiTietDonHang";
	}
	@ModelAttribute("loaisanpham")
	List<LoaiSanPham> listLSP() {
		return loaisanphamservice.findAll();
	}

}
