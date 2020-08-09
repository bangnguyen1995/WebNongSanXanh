package edu.poly.spring.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import edu.poly.spring.Dto.SanPhamDto;
import edu.poly.spring.models.Admin;
import edu.poly.spring.models.KhuyenMai;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.KhuyenMaiService;
import edu.poly.spring.services.LoaiSanPhamService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/adminsp")
public class AdminProductController {
	@Autowired
	private SanPhamService sanphamservice;
 @Autowired 
 private LoaiSanPhamService loaisanphamservice;
 @Autowired 
 private KhuyenMaiService khuyenmaiservice;
	@GetMapping("/listproducts")
	public String listproduct(ModelMap model, @RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 10);
		Page<SanPham> list = (Page<SanPham>) sanphamservice.findAll(pageable);
		for(SanPham sanpham:list) {
			Date hn = new Date();
			if(hn.compareTo(sanpham.getNgayhethan())>0) {
				sanpham.setTrangthai(false);
				sanphamservice.save(sanpham);
			}
			if(hn.compareTo(sanpham.getNgayhethan())<0) {
				sanpham.setTrangthai(true);
				sanphamservice.save(sanpham);
			}
		}
		model.addAttribute("products", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total",list.getTotalPages());
		return "admin/DanhSachSanPham";
	}

	@GetMapping("/findbyname")
	public String findbyname(ModelMap model, @RequestParam(defaultValue = "") String tensp,
			@RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 10);
		Page<SanPham> list = (Page<SanPham>) sanphamservice.findByTenSPLikeOrderByTenSP("%" + tensp + "%", pageable);
		if (list.getTotalElements() == 0) {
			model.addAttribute("total", list.getTotalElements());
			model.addAttribute("messager", "Không tìm thấy sản phẩm ");
		}
		model.addAttribute("products", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("tensp", tensp);
		model.addAttribute("totalpage",list.getTotalPages());
		System.out.print("ten san pham: " + tensp);
		return  "admin/DanhSachSanPham";
	}

	@GetMapping("/add")
	public String saveorupdate(ModelMap model,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		SanPhamDto sanphamdto = new SanPhamDto();
		model.addAttribute("sanphamDto", sanphamdto);
		return "admin/ThemSP2";
	}

	@PostMapping("/saveorupdate")
	public String save(ModelMap model, @Validated SanPhamDto sanphamDto,  BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("sanphamDto", sanphamDto);
			System.out.println("lỗi chưa nhập");
			return "admin/ThemSP2";
		}
		if (sanphamDto.getSoluong()<=0) {
			model.addAttribute("message", "Số lượng phải lớn hơn 0 !");
			model.addAttribute("sanphamDto", sanphamDto);
		
			return "admin/ThemSP2";
		}
		if (sanphamDto.getGiaSP()<=0) {
			model.addAttribute("message", "Giá sản phẩm phải lớn hơn 0 !");
			model.addAttribute("sanphamDto", sanphamDto);
		
			return "admin/ThemSP2";
		}
		if (sanphamDto.getImage().getOriginalFilename().length()==0) {
			model.addAttribute("message", "Bạn chưa chọn ảnh cho sản phẩm !");
			model.addAttribute("sanphamDto", sanphamDto);
		
			return "admin/ThemSP2";
		}
		if(sanphamDto.getNgaynhap().compareTo(sanphamDto.getNgayhethan())>0) {
			model.addAttribute("message", "Ngày hết hạn phải lớn hơn ngày nhập !");
			model.addAttribute("sanphamDto", sanphamDto);
			return "admin/ThemSP2";
		}
		String message = "Thêm mới thành công !";
		if(sanphamDto.getMaSP() != null && sanphamDto.getMaSP()  > 0) {
			message = "Cập nhật thành không !";
		}
		Path path = Paths.get("images/");

		try (InputStream inputStream = sanphamDto.getImage().getInputStream()) {
			Files.copy(inputStream, path.resolve(sanphamDto.getImage().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			String filename = sanphamDto.getImage().getOriginalFilename();
		} catch (Exception e) {
			model.addAttribute("message", "Lỗi lưu file !" + e.getMessage());
		}
		SanPham sanpham = new SanPham();
		sanpham.setMaSP(sanphamDto.getMaSP());
		sanpham.setTenSP(sanphamDto.getTenSP());
		sanpham.setSoluong(sanphamDto.getSoluong());
		sanpham.setGiaSP(sanphamDto.getGiaSP());
		KhuyenMai km = new KhuyenMai();
		km.setMaKM(sanphamDto.getMaKM());
		sanpham.setKhuyenmai(km);
		LoaiSanPham lsp = new LoaiSanPham();
		lsp.setMaLSP(sanphamDto.getMaLSP());
		sanpham.setLoaisanpham(lsp);
		sanpham.setMota(sanphamDto.getMota());
		sanpham.setTrangthai(sanphamDto.isTrangthai());
		sanpham.setNgayhethan(sanphamDto.getNgayhethan());
		sanpham.setNgaynhap(sanphamDto.getNgaynhap());
		sanpham.setAnhSP(sanphamDto.getImage().getOriginalFilename());
		sanphamservice.save(sanpham);
		model.addAttribute("sanphamDto", sanphamDto);
		model.addAttribute("message", message);
		return "admin/ThemSP2";
	}
	@PostMapping("/edit")
	public String edit(ModelMap model, @Validated SanPhamDto sanphamDto,  BindingResult result,HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("sanphamDto", sanphamDto);
			System.out.println("lỗi chưa nhập");
			return "admin/SuaSP";
		}
		if (sanphamDto.getSoluong()<=0) {
			model.addAttribute("message", "Số lượng phải lớn hơn 0 !");
			model.addAttribute("sanphamDto", sanphamDto);
		
			return "admin/SuaSP";
		}
		if (sanphamDto.getGiaSP()<=0) {
			model.addAttribute("message", "Giá sản phẩm phải lớn hơn 0 !");
			model.addAttribute("sanphamDto", sanphamDto);
		
			return "admin/SuaSP";
		}
		if(sanphamDto.getNgaynhap().compareTo(sanphamDto.getNgayhethan())>0) {
			model.addAttribute("message", "Ngày hết hạn phải lớn hơn ngày nhập !");
			model.addAttribute("sanphamDto", sanphamDto);
			return "admin/SuaSP";
		}
		String message = "Thêm mới thành công !";
		if(sanphamDto.getMaSP() != null && sanphamDto.getMaSP()  > 0) {
			message = "Cập nhật thành không !";
		}
		Path path = Paths.get("images/");

		try (InputStream inputStream = sanphamDto.getImage().getInputStream()) {
			Files.copy(inputStream, path.resolve(sanphamDto.getImage().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			String filename = sanphamDto.getImage().getOriginalFilename();
		
		} catch (Exception e) {
			model.addAttribute("message", "Lỗi lưu file !" + e.getMessage());
		}
		SanPham sp =  (SanPham) session.getAttribute("sp");
		SanPham sanpham = new SanPham();
		sanpham.setMaSP(sanphamDto.getMaSP());
		sanpham.setTenSP(sanphamDto.getTenSP());
		sanpham.setSoluong(sanphamDto.getSoluong());
		sanpham.setGiaSP(sanphamDto.getGiaSP());
		KhuyenMai km = new KhuyenMai();
		km.setMaKM(sanphamDto.getMaKM());
		sanpham.setKhuyenmai(km);
		LoaiSanPham lsp = new LoaiSanPham();
		lsp.setMaLSP(sanphamDto.getMaLSP());
		sanpham.setLoaisanpham(lsp);
		sanpham.setMota(sanphamDto.getMota());
		sanpham.setTrangthai(sanphamDto.isTrangthai());
		sanpham.setNgayhethan(sanphamDto.getNgayhethan());
		sanpham.setNgaynhap(sanphamDto.getNgaynhap());
		if(sanphamDto.getImage().getOriginalFilename().length()!=0) {
			sanpham.setAnhSP(sanphamDto.getImage().getOriginalFilename());
		}
		else {
			sanpham.setAnhSP(sp.getAnhSP());
		}
	
		sanphamservice.save(sanpham);
		model.addAttribute("sanphamDto", sanphamDto);
		model.addAttribute("message", message);
		session.setAttribute("sp", sanpham);
		return "admin/SuaSP";
	}

	@GetMapping("/find/{masp}")
	public String findbyid(ModelMap model, @PathVariable(name = "masp") Integer masp,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Optional<SanPham> list = sanphamservice.findById(masp);
		if (list.isPresent()) {
			SanPham sanpham = list.get();
			SanPhamDto sanphamdto = new SanPhamDto();
			sanphamdto.setMaSP(sanpham.getMaSP());
			sanphamdto.setGiaSP(sanpham.getGiaSP());
			sanphamdto.setMaKM(sanpham.getKhuyenmai().getMaKM());
			sanphamdto.setMaLSP(sanpham.getLoaisanpham().getMaLSP());
			sanphamdto.setMota(sanpham.getMota());
			sanphamdto.setNgayhethan(sanpham.getNgayhethan());
			sanphamdto.setNgaynhap(sanpham.getNgaynhap());
			sanphamdto.setSoluong(sanpham.getSoluong());
			sanphamdto.setTenSP(sanpham.getTenSP());
			sanphamdto.setTrangthai(sanpham.isTrangthai());
			model.addAttribute("sanphamDto", sanphamdto);
			session.setAttribute("sp", list.get());

		}

		return "admin/SuaSP";
	}

	@GetMapping("/delete/{masp}")
	public String delete(ModelMap model, @PathVariable(name = "masp") Integer masp,
			@RequestParam(defaultValue = "0") int page) {
		sanphamservice.deleteById(masp);
		return "redirect:/adminsp/listproducts";
	}

	@ModelAttribute(name = "loaisanpham")
	List<LoaiSanPham> getLoaisanphams() {
		return loaisanphamservice.findAll();
	}


	@ModelAttribute(name = "khuyenmai")
	List<KhuyenMai> getkhuyenmai() {
		return khuyenmaiservice.findAll();
	}
}
