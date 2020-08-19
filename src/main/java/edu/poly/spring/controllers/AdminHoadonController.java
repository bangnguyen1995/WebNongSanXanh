package edu.poly.spring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.HoaDon;
import edu.poly.spring.models.HoaDonChiTiet;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.HoaDonChiTietService;
import edu.poly.spring.services.HoaDonService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/adminhd")
public class AdminHoadonController {
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	HoaDonChiTietService hoadonchitietservice;
	@Autowired
	SanPhamService sanphamservice;

	@GetMapping("/listhdchuagiao")
	public String listhdchuagiao(ModelMap model, @RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 10);
		Page<HoaDon> listhd = (Page<HoaDon>) hoadonservice.findByTrangthai(false, pageable);
		Date date = new Date();
		String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date);

		model.addAttribute("listhd", listhd);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", listhd.getTotalPages());
		model.addAttribute("date", date2);
		return "admin/DanhSachHD";
	}

	@GetMapping("/listhddagiao")
	public String listhddagiao(ModelMap model, @RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 10);
		Page<HoaDon> listhd = (Page<HoaDon>) hoadonservice.findByTrangthai(true, pageable);
		Date date = new Date();
		String date2 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		model.addAttribute("listhd", listhd);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", listhd.getTotalPages());
		model.addAttribute("date", date2);
		return "admin/DanhSachHDDaGiao";
	}

	@GetMapping("/deletehd/{mahd}")
	public String deletehd(@PathVariable(name = "mahd") Integer mahd) {
		List<HoaDonChiTiet> listhdct = hoadonchitietservice.findByHoadonMaHD(mahd);
		for (HoaDonChiTiet hdct : listhdct) {
			Optional<SanPham> sanpham = sanphamservice.findById(hdct.getSanpham().getMaSP());
			SanPham sp = new SanPham();
			sp.setMaSP(sanpham.get().getMaSP());
			sp.setTenSP(sanpham.get().getTenSP());
			sp.setGiaSP(sanpham.get().getGiaSP());
			sp.setMota(sanpham.get().getMota());
			sp.setAnhSP(sanpham.get().getAnhSP());
			sp.setNgayhethan(sanpham.get().getNgayhethan());
			sp.setNgaynhap(sanpham.get().getNgaynhap());
			sp.setSoluong(sanpham.get().getSoluong() + hdct.getSoluongmua());
			sp.setKhuyenmai(sanpham.get().getKhuyenmai());
			sp.setLoaisanpham(sanpham.get().getLoaisanpham());
			sp.setTrangthai(sanpham.get().isTrangthai());
			sanphamservice.save(sp);
		}
		hoadonservice.deleteById(mahd);
		return "redirect:/adminhd/listhdchuagiao";
	}
	@GetMapping("/deletehddagiao/{mahd}")
	public String deletehddagiao(@PathVariable(name = "mahd") Integer mahd) {
		List<HoaDonChiTiet> listhdct = hoadonchitietservice.findByHoadonMaHD(mahd);
		for (HoaDonChiTiet hdct : listhdct) {
			Optional<SanPham> sanpham = sanphamservice.findById(hdct.getSanpham().getMaSP());
			SanPham sp = new SanPham();
			sp.setMaSP(sanpham.get().getMaSP());
			sp.setTenSP(sanpham.get().getTenSP());
			sp.setGiaSP(sanpham.get().getGiaSP());
			sp.setMota(sanpham.get().getMota());
			sp.setAnhSP(sanpham.get().getAnhSP());
			sp.setNgayhethan(sanpham.get().getNgayhethan());
			sp.setNgaynhap(sanpham.get().getNgaynhap());
			sp.setSoluong(sanpham.get().getSoluong() + hdct.getSoluongmua());
			sp.setKhuyenmai(sanpham.get().getKhuyenmai());
			sp.setLoaisanpham(sanpham.get().getLoaisanpham());
			sp.setTrangthai(sanpham.get().isTrangthai());
			sanphamservice.save(sp);
		}
		hoadonservice.deleteById(mahd);
		return "redirect:/adminhd/listhddagiao";
	}
	@GetMapping("/findbyhd/{mahd}")
	public String finbyhd(ModelMap model, @PathVariable(name = "mahd") Integer mahd, HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		List<HoaDonChiTiet> listhdct = hoadonchitietservice.findByHoadonMaHD(mahd);
		model.addAttribute("listhdct", listhdct);
		session.setAttribute("mahd", mahd);
		session.setAttribute("listhdct", listhdct);
		return "admin/ChiTietHoaDon";
	}

	@GetMapping("deletehdct/{mahdct}")
	public String deletehdct(ModelMap model, @PathVariable(name = "mahdct") Integer mahdct,HttpSession session) {
		Optional<HoaDonChiTiet> hdct = hoadonchitietservice.findById(mahdct);
		Optional<SanPham> sanpham = sanphamservice.findById(hdct.get().getSanpham().getMaSP());
		Optional<HoaDon> hoadon  =  hoadonservice.findById(hdct.get().getHoadon().getMaHD());
		HoaDon hd = new HoaDon();
		hd.setMaHD(hoadon.get().getMaHD());
		hd.setDiachinhan(hoadon.get().getDiachinhan());
		hd.setNgaydat(hoadon.get().getNgaydat());
		hd.setNguoidung(hoadon.get().getNguoidung());
		hd.setNguoinhan(hoadon.get().getNguoinhan());
		hd.setTrangthai(hoadon.get().isTrangthai());
		hd.setSdtNN(hoadon.get().getSdtNN());
		hd.setPhuongthucthanhtoan(hoadon.get().getPhuongthucthanhtoan());
		hd.setTongtien(hoadon.get().getTongtien()-((hdct.get().getSoluongmua()*hdct.get().getSanpham().getGiaSP())-
				(hdct.get().getSoluongmua()*hdct.get().getSanpham().getGiaSP()*hdct.get().getSanpham().getKhuyenmai().getGiamgia()/100)));
		hoadonservice.save(hd);
		SanPham sp = new SanPham();
		sp.setMaSP(sanpham.get().getMaSP());
		sp.setTenSP(sanpham.get().getTenSP());
		sp.setGiaSP(sanpham.get().getGiaSP());
		sp.setMota(sanpham.get().getMota());
		sp.setAnhSP(sanpham.get().getAnhSP());
		sp.setNgayhethan(sanpham.get().getNgayhethan());
		sp.setNgaynhap(sanpham.get().getNgaynhap());
		sp.setSoluong(sanpham.get().getSoluong() + hdct.get().getSoluongmua());
		sp.setKhuyenmai(sanpham.get().getKhuyenmai());
		sp.setLoaisanpham(sanpham.get().getLoaisanpham());
		sp.setTrangthai(sanpham.get().isTrangthai());
		sanphamservice.save(sp);
		hoadonchitietservice.deleteById(mahdct);
		Integer mahd = (Integer) session.getAttribute("mahd");
		return "redirect:/adminhd/findbyhd/" + mahd;
	}

	@GetMapping("/findhdbymahd/{mahd}")
	public String findhdbymahd(ModelMap model, @PathVariable(name = "mahd") Integer mahd, HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Optional<HoaDon> hoadon = hoadonservice.findById(mahd);
		model.addAttribute("hoadon", hoadon.get());
		session.setAttribute("trangthai", hoadon.get().isTrangthai());
		return "admin/CapNhatHD";
	}

	@PostMapping("/update")
	public String update(ModelMap model, HoaDon hoadon, HttpSession sesion) {
		hoadonservice.save(hoadon);
		Optional<HoaDon> hd = hoadonservice.findById(hoadon.getMaHD());
		model.addAttribute("hoadon", hd.get());
		model.addAttribute("message", "Cập nhật thành công");
		sesion.getAttribute("trangthai");
		return "admin/CapNhatHD";
	}

	@GetMapping("/seachhdchuagiao")
	public String seachhdchuagiao(ModelMap model, @RequestParam(defaultValue = "") String tennd,
			@RequestParam(defaultValue = "") String ngaydat, @RequestParam(defaultValue = "0") int page,HttpSession session)
			throws ParseException {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaydat);
		Pageable pageable = PageRequest.of(page, 10);
		Page<HoaDon> listhd = hoadonservice.findByNguoidungTenNDLikeAndNgaydatAndTrangthai("%" + tennd + "%", date1,
				false, pageable);
		model.addAttribute("listhd", listhd);
		model.addAttribute("date", ngaydat);
		model.addAttribute("tennd", tennd);
		model.addAttribute("curentPage", page);
		model.addAttribute("totalpage", listhd.getTotalPages());
		return "admin/DanhSachHD";
	}

	@GetMapping("/seachhddagiao")
	public String seachhddagiao(ModelMap model, @RequestParam(defaultValue = "") String tennd,
			@RequestParam(defaultValue = "") String ngaydat, @RequestParam(defaultValue = "0") int page,HttpSession session)
			throws ParseException {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaydat);
		Pageable pageable = PageRequest.of(page, 10);
		Page<HoaDon> listhd = hoadonservice.findByNguoidungTenNDLikeAndNgaydatAndTrangthai("%" + tennd + "%", date1,
				true, pageable);
		model.addAttribute("listhd", listhd);
		model.addAttribute("date", ngaydat);
		model.addAttribute("tennd", tennd);
		model.addAttribute("curentPage", page);
		model.addAttribute("totalpage", listhd.getTotalPages());
		return "admin/DanhSachHDDaGiao";
	}
}
