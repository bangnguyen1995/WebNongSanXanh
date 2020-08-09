package edu.poly.spring.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.spring.models.HinhAnhSP;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.services.HinhAnhSPService;
import edu.poly.spring.services.SanPhamService;




@Controller
public class ImageController {
	@Autowired
	private SanPhamService sanphamservice;
	@Autowired
	private HinhAnhSPService hinhanhspservice;
	
	@RequestMapping(value = "getimage/{id}", method= RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> dowloadLinkImage(@PathVariable Integer id){
		Optional<SanPham> sop = sanphamservice.findById(id);
		
		if (sop.isPresent()) {
		SanPham sanpham= sop.get();
			try {
				Path filename = Paths.get("images", sanpham.getAnhSP());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(bsr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "getanhsp/{id}", method= RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> dowload(@PathVariable Integer id){
		Optional<HinhAnhSP> sop = hinhanhspservice.findById(id);
		
		if (sop.isPresent()) {
		HinhAnhSP anhsp= sop.get();
			try {
				Path filename = Paths.get("images", anhsp.getAnhSP());
				byte[] buffer = Files.readAllBytes(filename);
				
				ByteArrayResource bsr = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(bsr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
