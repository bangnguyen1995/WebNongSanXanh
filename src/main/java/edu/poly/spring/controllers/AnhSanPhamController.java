package edu.poly.spring.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
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

import edu.poly.spring.Dto.AnhSanPhamDto;
import edu.poly.spring.models.HinhAnhSP;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.HinhAnhSPService;
import edu.poly.spring.services.SanPhamService;

@Controller
@RequestMapping("/anh")
public class AnhSanPhamController {
	@Autowired
	private HinhAnhSPService hinhanhsnphamservice;
	@Autowired
	private SanPhamService sanphamservice;

	@GetMapping({ "/findanhsp/{masp}/{page}", "/findanhsp/{masp}" })
	public String findanhsp(ModelMap model, @PathVariable(name = "masp") Integer masp,
			@PathVariable(name = "page", required = false) Optional<Integer> pageOpt,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		int page = 0;

		if (pageOpt.isPresent())
			page = pageOpt.get();
		Pageable pageable = PageRequest.of(page, 5);
		Page<HinhAnhSP> list = hinhanhsnphamservice.findBySanphamMaSP(masp, pageable);
		Optional<SanPham> sanpham = sanphamservice.findById(masp);
		AnhSanPhamDto anhspDto = new AnhSanPhamDto();
		anhspDto.setMaSP(sanpham.get().getMaSP());
		model.addAttribute("list", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("masp", masp);
		System.out.println("masp=" + masp);
		model.addAttribute("total", list.getTotalPages());
		model.addAttribute("hinhanhsp", anhspDto);
		model.addAttribute("sanpham", sanpham.get());
        session.setAttribute("masp", masp);
		return "admin/AnhSanPham";
	}

	@PostMapping("/save")
	public String save(@Validated AnhSanPhamDto anhspDto, ModelMap model, BindingResult rs) {
		if (rs.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("hinhanhsp", anhspDto);
			System.out.println("lỗi chưa nhập");
			return "redirect:findanhsp/" + anhspDto.getMaSP();
		}
		if (anhspDto.getAnh().getOriginalFilename().length() == 0) {
			model.addAttribute("message", "Bạn chưa chọn ảnh cho sản phẩm !");
			model.addAttribute("hinhanhsp", anhspDto);
			return "redirect:findanhsp/" + anhspDto.getMaSP();
		}
		Path path = Paths.get("images/");
		try (InputStream inputStream = anhspDto.getAnh().getInputStream()) {
			Files.copy(inputStream, path.resolve(anhspDto.getAnh().getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			String filename = anhspDto.getAnh().getOriginalFilename();
		} catch (Exception e) {
			model.addAttribute("message", "Lỗi lưu file !" + e.getMessage());
		}
		HinhAnhSP hinhanhsp = new HinhAnhSP();
		hinhanhsp.setMaIMG(anhspDto.getMaSP());
		hinhanhsp.setAnhSP(anhspDto.getAnh().getOriginalFilename());
		SanPham sanpham = new SanPham();
		sanpham.setMaSP(anhspDto.getMaSP());
		hinhanhsp.setSanpham(sanpham);
		model.addAttribute("hinhanhsp", anhspDto);
		hinhanhsnphamservice.save(hinhanhsp);
		return "redirect:findanhsp/" + anhspDto.getMaSP();
	}

	@GetMapping("/delete/{maha}")
	public String delete(@PathVariable(name = "maha") Integer maha,HttpSession session) {
		int masp = (int) session.getAttribute("masp");
		hinhanhsnphamservice.deleteById(maha);
	
		return "redirect:/anh/findanhsp/" + masp;
	}

}
