package edu.poly.spring.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.HoaDonChiTiet;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.reponsitories.HoaDonReponsitory;
import edu.poly.spring.services.HoaDonChiTietService;
import edu.poly.spring.services.HoaDonService;
import edu.poly.spring.services.NguoiDungService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/adminindex")
public class AdminIndexController {
	@Autowired
	SanPhamService sanphamservice;
	@Autowired
	NguoiDungService nguoidungservice;
	@Autowired
	HoaDonService hoadonservice;
	@Autowired
	HoaDonChiTietService hoadonchitietservice;

	@GetMapping("/index")
	public String index(ModelMap model, @RequestParam(defaultValue = "0") int page, HttpSession session) {
		if (session.getAttribute("adminacout") == null) {
			return "redirect:/admin/adminlogin";
		}
		Date date = new Date();
		long tongsp = sanphamservice.count();
		long tonghdmoi = hoadonservice.countByNgaydat(date);
		long tongnd = nguoidungservice.countByTrangthai(true);
		model.addAttribute("tonghdmoi", tonghdmoi);
		model.addAttribute("tongnd", tongnd);
		model.addAttribute("tongsp", tongsp);
		List<HoaDonChiTiet> hdct = hoadonchitietservice.findByHoadonNgaydat(date);
		int tongsoluongspban = 0;
		for (HoaDonChiTiet hoadonchitiet : hdct) {
			tongsoluongspban += hoadonchitiet.getSoluongmua();
		}
		model.addAttribute("soluongspban", tongsoluongspban);
		Pageable pageable = PageRequest.of(page, 5);
		Page<SanPham> listsp = (Page<SanPham>) sanphamservice.findBySoluongLessThan(50, pageable);
		model.addAttribute("listsp", listsp);
		model.addAttribute("listsize", listsp.getTotalElements());
		model.addAttribute("curentPage", page);
		model.addAttribute("totalpagesp", listsp.getTotalPages());
		List<SanPham> sp = sanphamservice.findAll();
		List<SanPham> sphethan = new ArrayList<>();

		for (SanPham sp2 : sp) {
			if (((sp2.getNgayhethan().getTime() - date.getTime()) / (24 * 3600 * 1000)) < 10) {
				Date hn = new Date();
				if (hn.compareTo(sp2.getNgayhethan()) > 0) {
					sp2.setTrangthai(false);
					sanphamservice.save(sp2);
			
				}
				if (hn.compareTo(sp2.getNgayhethan()) <= 0) {
					sp2.setTrangthai(true);
					sanphamservice.save(sp2);
					}
				sphethan.add(sp2);
				int songay = (int) ((sp2.getNgayhethan().getTime() - date.getTime()) / (24 * 3600 * 1000));

			}

			model.addAttribute("listsphethan", sphethan);
		}
		model.addAttribute("listsphethan", sphethan);
		model.addAttribute("listsizesphethan", sphethan.size());
		return "admin/index";
	}
}
