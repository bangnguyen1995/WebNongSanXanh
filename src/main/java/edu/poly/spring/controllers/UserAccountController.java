package edu.poly.spring.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.spring.models.ConfirmationToken;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.NguoiDung;
import edu.poly.spring.reponsitories.ConfirmationTokenRepository;
import edu.poly.spring.reponsitories.NguoiDungReponsitory;
import edu.poly.spring.services.EmailSenderService;
import edu.poly.spring.services.LoaiSanPhamService;
import edu.poly.spring.services.NguoiDungService;

@Controller
@RequestMapping("/nguoidung")
public class UserAccountController {
	@Autowired
	private NguoiDungReponsitory nguoidungreponsitory;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private NguoiDungService nguoidungService;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private LoaiSanPhamService loaisanphamService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView modelAndView) {
		modelAndView.addObject("user", new NguoiDung());
		modelAndView.setViewName("user/register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(ModelMap model, ModelAndView modelAndView, @Validated NguoiDung nguoidung,
			BindingResult result, @RequestParam("xacnhanmk") String xacnhanmk) {
		String sdt = "^[0-9]{0,99}$";
		String email = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		Optional<NguoiDung> existingUser = nguoidungreponsitory.findByEmailIgnoreCase(nguoidung.getEmail());
		if (result.hasErrors()) {
			model.addAttribute("message", "Vui lòng nhập tất cả các trường bắt buộc!");
			model.addAttribute("user", nguoidung);
			return "user/register";
		} else if (existingUser.isPresent()) {
			model.addAttribute("message", "Email đã tồn tại!");
			model.addAttribute("user", nguoidung);
			return "user/register";
		}
		else if (!nguoidung.getEmail().matches(email)) {
			model.addAttribute("message", "Sai định dạng Email!");
			model.addAttribute("user", nguoidung);
			return "user/register";
		}
		else if (!nguoidung.getSdt().matches(sdt)) {
			model.addAttribute("message", "Sai số điện thoại!");
			model.addAttribute("user", nguoidung);
			return "user/register";
		}   
		else if (!xacnhanmk.matches(nguoidung.getMatkhau())) {
			model.addAttribute("message", "Xác nhận mật khẩu sai!");
			model.addAttribute("user", nguoidung);
			return "user/register";
		} 
		else {
			nguoidungreponsitory.save(nguoidung);

			ConfirmationToken confirmationToken = new ConfirmationToken(nguoidung);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(nguoidung.getEmail());
			mailMessage.setSubject("Xác Nhận Tài Khoản - Nông Sản Xanh.");
			mailMessage.setFrom("cskh.nongsanxanhh@gmail.com");
			mailMessage.setText("Xin cảm ơn bạn đã đăng ký tài khoản Nông Sản Xanh.\n"
					+ "\n Để xác nhận tài khoản của bạn, xin vui lòng bấm vào đường dẫn bên dưới: \n\n"
					+ "http://localhost:8080/nguoidung/confirm-account?token=" + confirmationToken.getConfirmationToken()
					+ "\n\n Xin cảm ơn!"
					+ "\n Nông Sản Xanh - Hàng Việt Nam Chất Lượng Cao.");

			emailSenderService.sendEmail(mailMessage);
			model.addAttribute("email", nguoidung.getEmail());
			return "user/accountVerified";

		}

	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			Optional<NguoiDung> user = nguoidungreponsitory.findByEmailIgnoreCase(token.getNguoidung().getEmail());

			if (user.isPresent()) {
				user.get().setTrangthai(true);
				nguoidungreponsitory.save(user.get());
				modelAndView.setViewName("user/successfulRegisteration");
			}
		} else {
			modelAndView.addObject("message", "Đường dẫn xác nhận đã bị lỗi	!");
			modelAndView.setViewName("user/error");
		}

		return modelAndView;
	}

	@GetMapping("/listusers")
	public String listuser(ModelMap model, @RequestParam(defaultValue = "0") int page) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<NguoiDung> list = (Page<NguoiDung>) nguoidungService.findAll(pageable);
		model.addAttribute("users", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("total", list.getTotalPages());
		return "admin/DSNguoiDung";
	}

	@GetMapping("/findbynameuser")
	public String findbyname(ModelMap model, @RequestParam(defaultValue = "") String tennd,
			@RequestParam(defaultValue = "0") int page,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Pageable pageable = PageRequest.of(page, 10);
		Page<NguoiDung> list = (Page<NguoiDung>) nguoidungService.findByTenNDLikeOrderByTenND("%" + tennd + "%",
				pageable);
		if (list.getTotalElements() == 0) {
			model.addAttribute("total", list.getTotalElements());
			model.addAttribute("messager", "Không tìm thấy người dùng");
		}
		model.addAttribute("users", list);
		model.addAttribute("curentPage", page);
		model.addAttribute("tennd", tennd);
		model.addAttribute("totalpage", list.getTotalPages());
		System.out.print("ten nguoi dung: " + tennd);
		return "admin/DSNguoiDung";
	}

	@GetMapping("/find/{mand}")
	public String findbyid(ModelMap model, @PathVariable(name = "mand") Integer mand,HttpSession session) {
		if (session.getAttribute("adminacout")==null) {
			return "redirect:/admin/adminlogin";
		}
		Optional<NguoiDung> list = nguoidungService.findById(mand);
		if (list.isPresent()) {
			NguoiDung nguoidung = list.get();

			nguoidung.getMaND();
			nguoidung.getTenND();
			nguoidung.getDiachi();
			nguoidung.getSdt();

			nguoidung.getEmail();
			nguoidung.isGioitinh();
			nguoidung.getMatkhau();
			nguoidung.getNgaysinh();
			nguoidung.isTrangthai();

			model.addAttribute("nguoidung", nguoidung);

		}

		return "admin/AdEditUser";
	}

	@PostMapping("/saveorupdate")
	public String save(ModelMap model, NguoiDung nguoidung, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
			model.addAttribute("nguoidung", nguoidung);
			System.out.println("lỗi chưa nhập");
			return "admin/AdEditUser";
		}
		String message = "";
		if (nguoidung.getMaND() != null && nguoidung.getMaND() > 0) {
			message = "Cập nhật thành không !";
		}

		nguoidung.setMaND(nguoidung.getMaND());
		nguoidung.setTenND(nguoidung.getTenND());
		nguoidung.setDiachi(nguoidung.getDiachi());
		nguoidung.setSdt(nguoidung.getSdt());

		nguoidung.setEmail(nguoidung.getEmail());
		nguoidung.setGioitinh(nguoidung.isGioitinh());
		;
		nguoidung.setMatkhau(nguoidung.getMatkhau());
		nguoidung.setNgaysinh(nguoidung.getNgaysinh());
		nguoidung.setTrangthai(nguoidung.isTrangthai());

		model.addAttribute("nguoidung", nguoidung);
		nguoidungreponsitory.save(nguoidung);
		model.addAttribute("message", message);
		return "admin/AdEditUser";
	}

	@GetMapping("/delete/{mand}")
	public String delete(ModelMap model, @PathVariable(name = "mand") Integer mand,
			@RequestParam(defaultValue = "0") int page) {
		nguoidungService.deleteById(mand);

		return "redirect:/nguoidung/listusers";
	}
	
  	@RequestMapping("/profile")
  	public String profile(ModelMap model,
  			HttpSession session) {
  		NguoiDung nd =  (NguoiDung) session.getAttribute("nd");
  		model.addAttribute("nd", nd);
  		return "user/profile";
  	}
  	
  	//Thay đổi thông tin cá nhân - User
  	@RequestMapping("/capnhat/{mand}")
  	public String findbyiduser(ModelMap model,
  			@PathVariable(name = "mand") Integer mand) {
  		Optional<NguoiDung> list = nguoidungService.findById(mand);
  		model.addAttribute("nguoidung", list.get());
  		return "user/editprofile";
  	}
  	
  	//Thay đổi thông tin cá nhân - User
  	@PostMapping("/update")
  	public String updateUserAccount(ModelMap model, NguoiDung nguoidung, 
  			BindingResult result, HttpSession session) {
  		
  		if (result.hasErrors()) {
   			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin !");
   			model.addAttribute("nguoidung", nguoidung);
   			return "user/capnhat/" + nguoidung.getMaND();
   		}
  		
  		nguoidungreponsitory.save(nguoidung);
  		session.setAttribute("user", nguoidung);
  		return "user/profile";
  	}
  	
  	@GetMapping("/doimatkhau")
  	public String doimk() {
  		
  		return "user/editpassword";
  		
  	}
  	
  	//Thay đổi mật khẩu - User
  	@PostMapping("/capnhatmatkhau")
  	public String updateMatKhau(ModelMap model, HttpSession session, 
  			@RequestParam("matkhaucu") String matkhaucu, 
  			@RequestParam("matkhaumoi") String matkhaumoi, 
  			@RequestParam("xacnhanmatkhau") String xacnhanmk) {
  		
  		NguoiDung nd = (NguoiDung) session.getAttribute("user");
  		if (matkhaucu.isEmpty() && matkhaumoi.isEmpty() && xacnhanmk.isEmpty()) {
			model.addAttribute("message", "Hãy nhập đầy đủ các trường!");
			return "user/editpassword";
		} else if (!nd.getMatkhau().matches(matkhaucu)) {
			System.out.println(matkhaucu);
			System.out.println(nd.getMatkhau());
			model.addAttribute("message", "Mật khẩu cũ không chính xác!");
			return "user/editpassword";
		} else if (!matkhaumoi.matches(xacnhanmk)) {
			model.addAttribute("message", "Mật khẩu xác nhận không chính xác!");
			return "user/editpassword";
		} 
  		
  		nguoidungreponsitory.updateNguoiDungSetMatKhauForMaND(matkhaumoi, nd.getMaND());

  		model.addAttribute("message", "Cập nhật mật khẩu thành công!");
  		return "user/editpassword";
  	}
  	@RequestMapping("/quenmatkhau")
  	public String forgotPassword() {
  		return "user/forgotpassword";
  	}
  	
  //Quên mật khẩu
  	@PostMapping("/quenmatkhau")
  	public String forgotPassword2(ModelMap model, @RequestParam("email") String email, HttpSession session) {
  		String checkemail = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
  		Optional<NguoiDung> nd = nguoidungreponsitory.findByEmailIgnoreCase(email);
  		if (email.isEmpty()) {
			model.addAttribute("message", "Vui lòng nhập Email!");
			return "user/forgotpassword";
		} else if (!nd.isPresent()) {
			model.addAttribute("message", "Email chưa đăng ký!");
			return "user/forgotpassword";
		} else if (!email.matches(checkemail)) {
			model.addAttribute("message", "Sai định dạng Email!");
			return "user/forgotpassword";
		} else {
			ConfirmationToken token = confirmationTokenRepository.findBynguoidungMaND(nd.get().getMaND());
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(nd.get().getEmail());
			mailMessage.setSubject("Mã Xác Nhận - Nông Sản Xanh");
			mailMessage.setFrom("cskh.nongsanxanhh@gmail.com");
			mailMessage.setText("Mã xác nhận.\n"
								+ "Mã: " + token.getConfirmationToken()
								+ "\n\n Xin cảm ơn!"
								+ "\n Nông Sản Xanh - Hàng Việt Nam Chất Lượng Cao.");
			emailSenderService.sendEmail(mailMessage);
			session.setAttribute("maND", nd.get().getMaND());
			return "user/confirmToken";
		}
  	}
  	
  	//Xác nhận mã token để lấy mật khẩu
  	@PostMapping("/xacnhanma")
  	public String xacnhanmatoken(ModelMap model, @RequestParam("maxacnhan") String token,HttpSession session) {
  		
  		int mand =  (int) session.getAttribute("maND");
  		ConfirmationToken token2 = confirmationTokenRepository.findBynguoidungMaND(mand);
  		ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
  		
  		if (token.isEmpty()) {
			model.addAttribute("message", "Vui lòng nhập mã xác nhận");
			return "user/confirmToken";
		} else if (confirmationToken == null) {
			model.addAttribute("message","Mã xác nhận không tồn tại");
			return "user/confirmToken";
		} else if (!confirmationToken.getConfirmationToken().matches(token2.getConfirmationToken())) {
			model.addAttribute("message","Mã xác nhận không đúng");
			return "user/confirmToken";
		} else {
			return "user/updatepasswordbytoken";
		}
  	}
  	
  	@PostMapping("datlaimatkhau")
  	public String datlatmk(ModelMap model, HttpSession session, 
  			@RequestParam("xacnhanmatkhau") String xacnhanmk, 
  			@RequestParam("matkhau") String matkhau) {
  		
  		int mand = (int) session.getAttribute("maND");
  		
  		if (matkhau.isEmpty() && xacnhanmk.isEmpty()) {
			model.addAttribute("message", "Vui lòng nhập đầy đủ các trường");
			return "user/updatepasswordbytoken";
		} else if (!xacnhanmk.matches(matkhau)) {
			model.addAttribute("message", "Mật khẩu xác nhận không chính xác!");
			return "user/updatepasswordbytoken"; 
		} else {
			nguoidungreponsitory.updateNguoiDungSetMatKhauForMaND(matkhau, mand);
			return "redirect:/login";
		}
  	}
	@ModelAttribute("loaisanpham")
	List<LoaiSanPham> listLSP() {
		return loaisanphamService.findAll();
	}
}
