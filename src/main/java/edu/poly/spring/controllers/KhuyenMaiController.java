package edu.poly.spring.controllers;

import java.util.Date;
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

import edu.poly.spring.Dto.KhuyenMaiDto;
import edu.poly.spring.models.KhuyenMai;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.KhuyenMaiService;

@Controller
@RequestMapping("/promotions")
public class KhuyenMaiController {
	@Autowired
	private KhuyenMaiService khuyenmaiService;
	@GetMapping("/add")
	public String add(ModelMap model,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		KhuyenMaiDto khuyenmaiDto = new KhuyenMaiDto();
		model.addAttribute("khuyenmaiDto", khuyenmaiDto);
		return "promotions/addPromotion";
	}
	@GetMapping("/findbyname")
	public String findbyname(ModelMap model, @RequestParam(defaultValue = "") String tenkm,
			@RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 5);
		Page<KhuyenMai> list = (Page<KhuyenMai>) khuyenmaiService.findByTenKMLikeOrderByTenKM("%" + tenkm + "%", pageable);
		if (list.getTotalElements() == 0) {
			model.addAttribute("total", list.getTotalElements());
			model.addAttribute("message", "Không tìm thấy khuyến mãi nào");
		}
		model.addAttribute("promotions", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("tenkm", tenkm);
		model.addAttribute("totalpage",list.getTotalPages());
		return "promotions/listkhuyenMai";
	}
	@GetMapping("/listpromotions")
	public String listpromotion(ModelMap model, @RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 5);
		Page<KhuyenMai> list = (Page<KhuyenMai>) khuyenmaiService.findAll(pageable);
		for(KhuyenMai km:list) {
			Date hn = new Date();
			if(hn.compareTo(km.getNgayketthuc())>0) {
				km.setTrangthai(false);
				km.setGiamgia(0);
				khuyenmaiService.save(km);
			}
			if(hn.compareTo(km.getNgayketthuc())<0) {
				km.setTrangthai(true);
				khuyenmaiService.save(km);
			}}
		model.addAttribute("promotions", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total",list.getTotalPages());
		return "promotions/listkhuyenMai";
	}
	
	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated KhuyenMaiDto khuyenmaiDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/addPromotion";			
		}
		
		Optional<KhuyenMai> opKM = khuyenmaiService.findById(khuyenmaiDto.getMaKM());
		if(opKM.isPresent()) {
			model.addAttribute("message", "Mã khuyễn mãi đã tồn tại");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/addPromotion";	
		}
		String message = "Thêm thành công";
		if(khuyenmaiDto.getMaKM() != null ) {
			message = "Thêm thành công !";
		}
		if(khuyenmaiDto.getNgaybatdau().compareTo(khuyenmaiDto.getNgayketthuc())>0) {
			model.addAttribute("message", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/addPromotion";
		}
		if(khuyenmaiDto.getGiamgia()<0) {
			model.addAttribute("message", "Giảm giá phải lớn hơn hoặc bằng 0");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/addPromotion";
		}
		KhuyenMai km = new KhuyenMai();
		km.setMaKM(khuyenmaiDto.getMaKM());
		km.setTenKM(khuyenmaiDto.getTenKM());
		km.setNgaybatdau(khuyenmaiDto.getNgaybatdau());
		km.setNgayketthuc(khuyenmaiDto.getNgayketthuc());
		km.setChitietKM(khuyenmaiDto.getChitietKM());
		km.setGiamgia(khuyenmaiDto.getGiamgia());
	
		khuyenmaiService.save(km);
		model.addAttribute("khuyenmaiDto", khuyenmaiDto);
		model.addAttribute("message", message);
		return "promotions/addPromotion";
	}
	@PostMapping("/Update")
	public String Update(ModelMap model, @Validated KhuyenMaiDto khuyenmaiDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/editPromotion";			
		}
		
		String message = "Thêm thành công";
		if(khuyenmaiDto.getMaKM() != null ) {
			message = "Sửa thành công !";
		}
		if(khuyenmaiDto.getNgaybatdau().compareTo(khuyenmaiDto.getNgayketthuc())>0) {
			model.addAttribute("message", "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/editPromotion";
		}
		if(khuyenmaiDto.getGiamgia()<0) {
			model.addAttribute("message", "Giảm giá phải lớn hơn hoặc bằng 0");
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);
			return "promotions/editPromotion";
		}
		KhuyenMai km = new KhuyenMai();
		km.setMaKM(khuyenmaiDto.getMaKM());
		km.setTenKM(khuyenmaiDto.getTenKM());
		km.setNgaybatdau(khuyenmaiDto.getNgaybatdau());
		km.setNgayketthuc(khuyenmaiDto.getNgayketthuc());
		km.setChitietKM(khuyenmaiDto.getChitietKM());
		km.setGiamgia(khuyenmaiDto.getGiamgia());
	
		khuyenmaiService.save(km);
		model.addAttribute("khuyenmaiDto", khuyenmaiDto);
		model.addAttribute("message", message);
		return "promotions/editPromotion";
	}
	
	
	@GetMapping("/edit/{makm}")
	public String findbyid(ModelMap model, @PathVariable(name = "makm") String makm,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Optional<KhuyenMai> opKM = khuyenmaiService.findById(makm);
		if(opKM.isPresent()) {
			KhuyenMai km = opKM.get();
			KhuyenMaiDto khuyenmaiDto = new KhuyenMaiDto();
			khuyenmaiDto.setMaKM(km.getMaKM());
			khuyenmaiDto.setTenKM(km.getTenKM());
			khuyenmaiDto.setNgaybatdau(km.getNgaybatdau());
			khuyenmaiDto.setNgayketthuc(km.getNgayketthuc());
			khuyenmaiDto.setGiamgia(km.getGiamgia());
			khuyenmaiDto.setChitietKM(km.getChitietKM());
			model.addAttribute("khuyenmaiDto", khuyenmaiDto);			
		}
		
		return "promotions/editPromotion";
	}
	@GetMapping("/delete/{makm}")
	public String delete(ModelMap model, @PathVariable(name = "makm") String makm,
			@RequestParam(defaultValue = "0") int page) {
		khuyenmaiService.deleteById(makm);
		return "redirect:/promotions/listpromotions";
	}
	@RequestMapping("/list")
	public String list() {
		return "list";
	}
	@RequestMapping("/find")
	public String find() {
		return "find";
	}
}
