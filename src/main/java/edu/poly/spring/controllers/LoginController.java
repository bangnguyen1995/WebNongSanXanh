package edu.poly.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.models.NguoiDung;

import edu.poly.spring.reponsitories.NguoiDungReponsitory;

@Controller
public class LoginController {
	@Autowired
private NguoiDungReponsitory nguoidungreponsitory;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		session.removeAttribute("user");
		return "login/login";
	}
	
	@RequestMapping(value="/dangky", method = RequestMethod.GET)
	public String logins(HttpSession session) {
		session.removeAttribute("user");
		return "user/register";
	}
	
	@RequestMapping(value="/login", method= {RequestMethod.POST})
	public String login(ModelMap model, 
			@RequestParam("emailId") String email,
			@RequestParam("password") String password,HttpSession session) {

		Optional<NguoiDung> optUser = nguoidungreponsitory.findByEmailIgnoreCase(email);
		
		if (optUser.isPresent()) {
			if (optUser.get().getMatkhau().equals(password)&&optUser.get().isTrangthai()==true) {
				model.addAttribute("emailId", optUser.get());
				session.setAttribute("user", optUser.get());
				session.removeAttribute("cart");
				 return "redirect:/index";
			} 
			else if (optUser.get().isTrangthai()==false) {
				model.addAttribute("message", "Tài khoản chưa được kích hoạt !");
				return "login/login";
			}
			else {
				model.addAttribute("message", "Sai tài khoản hoặc mật khẩu !");
				return "login/login";
			}
		} else {
			model.addAttribute("message", "Tài khoản này chưa đăng ký !");
			return "login/login";
		}
	}
}
