package edu.poly.spring.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import edu.poly.spring.models.KhuyenMai;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.SanPham;

public interface SanPhamService {

	List<SanPham> findByLoaisanphamMaLSP(String malsp);

	List<SanPham> findTop10SanPhamByMaLSP(String malsp, Integer masp);

	List<SanPham> findTop10SanPhamOrderByDanhGia();

	List<SanPham> findTop10SanPhamOrderByNgayNhap();

	List<SanPham> findTop12SanPhamBySUMSoLuong();

	Page<SanPham> findSanPhamByMaMK(Pageable pageable);

	List<SanPham> findTop10SanPhamByMaMK();

	Page<SanPham> findSanPhamByMaLSP(String malsp, Pageable pageable);

	Page<SanPham> findBySoluongLessThan(int soluong, Pageable pageable);

	List<SanPham> findAll();

	Page<SanPham> findByTenSPLikeOrderByTenSP(String tensp, Pageable pageable);

	void deleteAll();

	void deleteAll(Iterable<? extends SanPham> entities);

	void delete(SanPham entity);

	void deleteById(Integer id);

	long count();

	Iterable<SanPham> findAllById(Iterable<Integer> ids);

	Page<SanPham> findAll(Pageable pageable);

	boolean existsById(Integer id);

	Optional<SanPham> findById(Integer id);

	<S extends SanPham> Iterable<S> saveAll(Iterable<S> entities);

	SanPham save(SanPham entity);

	Page<Object[]> findSanphamBannhieunhat(String ngaydat1, String ngaydat2, Pageable pageable);





	


	









}
