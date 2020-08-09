package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.LoaiSanPham;

public interface LoaiSanPhamService {

	void deleteAll();

	void deleteAll(Iterable<? extends LoaiSanPham> entities);

	void delete(LoaiSanPham entity);

	<S extends LoaiSanPham> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(String id);

	long count();

	<S extends LoaiSanPham> List<S> findAll(Example<S> example);

	<S extends LoaiSanPham> boolean exists(Example<S> example);

	<S extends LoaiSanPham> long count(Example<S> example);

	LoaiSanPham getOne(String id);

	void deleteAllInBatch();

	<S extends LoaiSanPham> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<LoaiSanPham> entities);

	boolean existsById(String id);

	<S extends LoaiSanPham> S saveAndFlush(S entity);

	void flush();

	Optional<LoaiSanPham> findById(String id);

	<S extends LoaiSanPham> List<S> saveAll(Iterable<S> entities);

	List<LoaiSanPham> findAllById(Iterable<String> ids);

	List<LoaiSanPham> findAll(Sort sort);

	List<LoaiSanPham> findAll();

	Page<LoaiSanPham> findAll(Pageable pageable);

	<S extends LoaiSanPham> Optional<S> findOne(Example<S> example);

	LoaiSanPham save(LoaiSanPham entity);

	Page<LoaiSanPham> findByTenLSPLikeOrderByTenLSP(String tenLSP, Pageable pageable);

}
