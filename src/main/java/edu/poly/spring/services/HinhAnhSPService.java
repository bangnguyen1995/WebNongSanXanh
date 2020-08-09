package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.HinhAnhSP;

public interface HinhAnhSPService {

	void deleteAll();

	void deleteAll(Iterable<? extends HinhAnhSP> entities);

	void delete(HinhAnhSP entity);

	<S extends HinhAnhSP> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends HinhAnhSP> List<S> findAll(Example<S> example);

	<S extends HinhAnhSP> boolean exists(Example<S> example);

	<S extends HinhAnhSP> long count(Example<S> example);

	HinhAnhSP getOne(Integer id);

	void deleteAllInBatch();

	<S extends HinhAnhSP> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<HinhAnhSP> entities);

	boolean existsById(Integer id);

	<S extends HinhAnhSP> S saveAndFlush(S entity);

	void flush();

	Optional<HinhAnhSP> findById(Integer id);

	<S extends HinhAnhSP> List<S> saveAll(Iterable<S> entities);

	List<HinhAnhSP> findAllById(Iterable<Integer> ids);

	List<HinhAnhSP> findAll(Sort sort);

	List<HinhAnhSP> findAll();

	Page<HinhAnhSP> findAll(Pageable pageable);

	<S extends HinhAnhSP> Optional<S> findOne(Example<S> example);

	HinhAnhSP save(HinhAnhSP entity);

	Page<HinhAnhSP> findBySanphamMaSP(Integer masp, Pageable pageable);

	List<HinhAnhSP> findHinhAnhSanPhamByMaSP(Integer masp);



}
