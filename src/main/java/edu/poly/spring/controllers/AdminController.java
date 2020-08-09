package edu.poly.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.Dto.AdminDto;
import edu.poly.spring.models.Admin;

import edu.poly.spring.services.AdminAccountService;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminAccountService adminAccountService;

	@RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
	public String adminLogin() {
		return "login/signinadmin";
	}

	@RequestMapping(value = "/adminlogin", method = { RequestMethod.POST })
	public String login1(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {

		Optional<Admin> ad = adminAccountService.findByUsernameAndPassword(username, password);

		if (ad.isPresent()) {

			session.setAttribute("adminacout", ad.get());
			return "redirect:/adminindex/index";
		} else {
			model.addAttribute("message", "Sai tài khoản hoặc mật khẩu !");
			return "login/signinadmin";
		}
	}

	@GetMapping("/addManager")
	public String add(ModelMap model, HttpSession session) {
		if (session.getAttribute("adminacout") == null) {
			return "redirect:/admin/adminlogin";
		}
		AdminDto adminDto = new AdminDto();
		model.addAttribute("adminDto", adminDto);
		return "admin/SignUpAdmin";
	}

	@PostMapping("/saveOrUpdate")
	public String addManager(ModelMap model, @Validated AdminDto adminDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("adminDto", adminDto);
			return "admin/SignUpAdmin";
		}
		Optional<Admin> opAdmin = adminAccountService.findById(adminDto.getUsername());
		if (opAdmin.isPresent()) {
			model.addAttribute("message", "Tài khoản đã được đăng ký");
			model.addAttribute("adminDto", adminDto);
			return "admin/SignUpAdmin";
		}
		String message = "Thêm quản lý mới thành công";
		Admin ad = new Admin();
		ad.setUsername(adminDto.getUsername());
		ad.setPassword(adminDto.getPassword());
		adminAccountService.save(ad);
		model.addAttribute("adminDto", adminDto);
		model.addAttribute("message", message);
		return "admin/SignUpAdmin";
	}

	@GetMapping("/changepassworld")
	public String changepassworld(HttpSession session) {
		if (session.getAttribute("adminacout") == null) {
			return "redirect:/admin/adminlogin";
		}
		return "/admin/Thaydoimatkhau";
	}

	@PostMapping("/thaydoimatkhau")
	public String thaydoimatkhau(ModelMap model, HttpSession session, @RequestParam(defaultValue = "") String xnmkmoi,
			@RequestParam(defaultValue = "") String mkmoi, @RequestParam(defaultValue = "") String mkcu) {
		Admin ad = (Admin) session.getAttribute("adminacout");
		if (!mkcu.equals(ad.getPassword())) {
			model.addAttribute("messeger", "Sai tên mật khẩu cũ");
			model.addAttribute("mkcu", mkcu);
			model.addAttribute("mkmoi", mkmoi);
			model.addAttribute("xnmkmoi", xnmkmoi);
			return "/admin/Thaydoimatkhau";
		}
		else if  (!mkmoi.equals(xnmkmoi)) {
			model.addAttribute("messeger", "Mật khẩu mới không trùng khớp");
			model.addAttribute("mkcu", mkcu);
			model.addAttribute("mkmoi", mkmoi);
			model.addAttribute("xnmkmoi", xnmkmoi);
			return "/admin/Thaydoimatkhau";
		}
		
		Admin admin = new Admin();
		admin.setUsername(ad.getUsername());
		admin.setPassword(mkmoi);
		adminAccountService.save(admin);
		model.addAttribute("messeger", "Cập nhật thành công!");
		session.setAttribute("adminacout", admin);
		return "/admin/Thaydoimatkhau";
	}
}
