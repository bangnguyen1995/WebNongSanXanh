package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.DanhGia;

public interface DanhGiaService {

	void deleteAll();

	void deleteAll(Iterable<? extends DanhGia> entities);

	void delete(DanhGia entity);

	<S extends DanhGia> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends DanhGia> List<S> findAll(Example<S> example);

	<S extends DanhGia> boolean exists(Example<S> example);

	<S extends DanhGia> long count(Example<S> example);

	DanhGia getOne(Integer id);

	void deleteAllInBatch();

	<S extends DanhGia> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<DanhGia> entities);

	boolean existsById(Integer id);

	<S extends DanhGia> S saveAndFlush(S entity);

	void flush();

	Optional<DanhGia> findById(Integer id);

	<S extends DanhGia> List<S> saveAll(Iterable<S> entities);

	List<DanhGia> findAllById(Iterable<Integer> ids);

	List<DanhGia> findAll(Sort sort);

	List<DanhGia> findAll();

	Page<DanhGia> findAll(Pageable pageable);

	<S extends DanhGia> Optional<S> findOne(Example<S> example);

	DanhGia save(DanhGia entity);

}
