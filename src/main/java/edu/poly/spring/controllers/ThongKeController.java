package edu.poly.spring.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.HoaDon;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.reponsitories.NguoiDungReponsitory;
import edu.poly.spring.services.HoaDonService;
import edu.poly.spring.services.SanPhamService;

@Controller
public class ThongKeController {
	@Autowired
	private HoaDonService hoadonservice;
	@Autowired
	private SanPhamService sanphamservice;
	@Autowired
	private NguoiDungReponsitory nguoidungreponsitory;
	@GetMapping("/thongkehd")
	public String thongkehd(ModelMap model, @RequestParam(defaultValue = "0") Integer page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable  = PageRequest.of(page, 10);
		Date date1 = new Date();
		Date date2 = new Date();
		String ngaydat1 = new SimpleDateFormat("yyyy-MM-dd").format(date1);
		String ngaydat2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
		Page <HoaDon> listhd =  hoadonservice.findByNgaydatBetweenAndTrangthai(date1, date2, true, pageable);
		double tongthu = 0;
		for(HoaDon hd : listhd) {
			tongthu += hd.getTongtien();
		}
		model.addAttribute("listhd",listhd);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", listhd.getTotalPages());
		model.addAttribute("date1", ngaydat1);
		model.addAttribute("date2", ngaydat2);
		model.addAttribute("tongthu", tongthu);
		return "admin/ThongKeHD";
	}
	@GetMapping("/timkiemthongkehd")
	public String timkiemthongkehd(ModelMap model,@RequestParam(defaultValue = "0") String ngaydat1, @RequestParam(defaultValue = "0") String ngaydat2, 
	@RequestParam(defaultValue = "0") Integer page,HttpSession session) throws ParseException {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable  = PageRequest.of(page, 10);
		Date date = new Date();
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaydat1);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaydat2);
		Page <HoaDon> listhd =  hoadonservice.findByNgaydatBetweenAndTrangthai(date1, date2, true, pageable);
		double tongthu = 0;
		for(HoaDon hd : listhd) {
			tongthu += hd.getTongtien();
		}
		model.addAttribute("listhd",listhd);
		model.addAttribute("curentPage", page);
		model.addAttribute("totalpage", listhd.getTotalPages());
		model.addAttribute("date1", ngaydat1);
		model.addAttribute("date2", ngaydat2);
		model.addAttribute("tongthu", tongthu);
		return "admin/ThongKeHD";
	}
	@GetMapping("/thongkesp")
	public String thongkesp(ModelMap model , @RequestParam(defaultValue = "0") Integer page,HttpSession session) throws ParseException {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable  = PageRequest.of(page, 10);
		String ngaydat1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String ngaydat2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Page<Object[]> listtksp =  sanphamservice.findSanphamBannhieunhat(ngaydat1,ngaydat2, pageable);
		model.addAttribute("listtksp",listtksp);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", listtksp.getTotalPages());
		model.addAttribute("date1", ngaydat1);
		model.addAttribute("date2", ngaydat2);
		return "admin/ThongKeSP";
	}
	@GetMapping("/timkiemthongkesp")
	public String timkiemthongkesp(ModelMap model ,@RequestParam(defaultValue = "0") String ngaydat1,@RequestParam(defaultValue = "0") String ngaydat2, @RequestParam(defaultValue = "0") Integer page,
			HttpSession session) throws ParseException {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable  = PageRequest.of(page, 10);
		Page<Object[]> listtksp =  sanphamservice.findSanphamBannhieunhat(ngaydat1,ngaydat2, pageable);
		model.addAttribute("listtksp",listtksp);
		model.addAttribute("curentPage", page);
		model.addAttribute("totalpage", listtksp.getTotalPages());
		model.addAttribute("date1", ngaydat1);
		model.addAttribute("date2", ngaydat2);
		return "admin/ThongKeSP";
	}
	@GetMapping("/thongkekh")
	public String thongkekh(ModelMap model,@RequestParam (defaultValue = "0") Integer page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable  = PageRequest.of(page, 10);
		Page<Object[]> listthkh = nguoidungreponsitory.findNguoidungmuanhieunhat(true, pageable);
		model.addAttribute("listtkkh",listthkh);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", listthkh.getTotalPages());
		return "admin/ThongKeKH";
	}
}
