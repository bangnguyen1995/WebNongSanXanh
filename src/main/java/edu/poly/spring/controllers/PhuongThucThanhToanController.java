package edu.poly.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.spring.Dto.PhuongThucThanhToanDto;
import edu.poly.spring.models.PhuongThucThanhToan;
import edu.poly.spring.services.PhuongThucThanhToanService;



@Controller
@RequestMapping("/payments")
public class PhuongThucThanhToanController {
	@Autowired
	private PhuongThucThanhToanService ptttService;

	@GetMapping("/listPayment")
	public String listpttt(ModelMap model) {
		List<PhuongThucThanhToan> list = ptttService.findAll();
		model.addAttribute("list", list);
		return "payments/listPayment";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		PhuongThucThanhToanDto ptttDto = new PhuongThucThanhToanDto();
		model.addAttribute("ptttDto", ptttDto);
		return "payments/addPayment";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @Validated PhuongThucThanhToanDto ptttDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin");
			model.addAttribute("ptttDto", ptttDto);
			return "payments/addPayment";
		}
		
		PhuongThucThanhToan pttt = new PhuongThucThanhToan();
		pttt.setMaPT(ptttDto.getMaPT());
		pttt.setTenPT(ptttDto.getTenPT());
		pttt.setMota(ptttDto.getMota());
		ptttService.save(pttt);
		model.addAttribute("ptttDto", ptttDto);
		String message = " Thêm mới thành công";
		model.addAttribute("message", message);
		
		return "payments/addPayment";
	}
	@PostMapping("/update")
	public String update(ModelMap model, @Validated PhuongThucThanhToanDto ptttDto, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Xin hãy nhập đầy đủ thông tin");
			model.addAttribute("ptttDto", ptttDto);
			return "payments/addPayment";
		}
		
		PhuongThucThanhToan pttt = new PhuongThucThanhToan();
		pttt.setMaPT(ptttDto.getMaPT());
		pttt.setTenPT(ptttDto.getTenPT());
		pttt.setMota(ptttDto.getMota());
		ptttService.save(pttt);
		model.addAttribute("ptttDto", ptttDto);
		String message = "Sửa thành công";
		model.addAttribute("message", message);
		
		return "payments/editPayment";
	}

	@GetMapping("/delete/{mapt}")
	public String delete(ModelMap model, @PathVariable(name = "mapt") Integer mapt,
			@RequestParam(defaultValue = "0") int page) {
		ptttService.deleteById(mapt);
		return listpttt(model);
	}

	@GetMapping("/edit/{mapt}")
	public String edit(ModelMap model, @PathVariable(name = "mapt") Integer mapt) {
		Optional<PhuongThucThanhToan> opPTTT = ptttService.findById(mapt);
		if (opPTTT.isPresent()) {
			PhuongThucThanhToan pttt = opPTTT.get();
			PhuongThucThanhToanDto ptttDto = new PhuongThucThanhToanDto();
			ptttDto.setMaPT(pttt.getMaPT());
			ptttDto.setTenPT(pttt.getTenPT());
			ptttDto.setMota(pttt.getMota());
			model.addAttribute("ptttDto", ptttDto);

		}
		return "payments/editPayment";
	}
}
