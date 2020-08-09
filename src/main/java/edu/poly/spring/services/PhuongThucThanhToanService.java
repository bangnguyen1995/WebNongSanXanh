package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.PhuongThucThanhToan;

public interface PhuongThucThanhToanService {

	void deleteAll();

	void deleteAll(Iterable<? extends PhuongThucThanhToan> entities);

	void delete(PhuongThucThanhToan entity);

	<S extends PhuongThucThanhToan> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends PhuongThucThanhToan> List<S> findAll(Example<S> example);

	<S extends PhuongThucThanhToan> boolean exists(Example<S> example);

	<S extends PhuongThucThanhToan> long count(Example<S> example);

	PhuongThucThanhToan getOne(Integer id);

	void deleteAllInBatch();

	<S extends PhuongThucThanhToan> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<PhuongThucThanhToan> entities);

	boolean existsById(Integer id);

	<S extends PhuongThucThanhToan> S saveAndFlush(S entity);

	void flush();

	Optional<PhuongThucThanhToan> findById(Integer id);

	<S extends PhuongThucThanhToan> List<S> saveAll(Iterable<S> entities);

	List<PhuongThucThanhToan> findAllById(Iterable<Integer> ids);

	List<PhuongThucThanhToan> findAll(Sort sort);

	List<PhuongThucThanhToan> findAll();

	Page<PhuongThucThanhToan> findAll(Pageable pageable);

	<S extends PhuongThucThanhToan> Optional<S> findOne(Example<S> example);

	PhuongThucThanhToan save(PhuongThucThanhToan entity);

}
