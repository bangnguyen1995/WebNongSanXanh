package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.KhuyenMai;

public interface KhuyenMaiService {

	void deleteAll();

	void deleteAll(Iterable<? extends KhuyenMai> entities);

	void delete(KhuyenMai entity);

	<S extends KhuyenMai> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(String makm);

	long count();

	<S extends KhuyenMai> List<S> findAll(Example<S> example);

	<S extends KhuyenMai> boolean exists(Example<S> example);

	<S extends KhuyenMai> long count(Example<S> example);

	KhuyenMai getOne(String id);

	void deleteAllInBatch();

	<S extends KhuyenMai> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<KhuyenMai> entities);

	boolean existsById(String id);

	<S extends KhuyenMai> S saveAndFlush(S entity);

	void flush();

	Optional<KhuyenMai> findById(String id);

	<S extends KhuyenMai> List<S> saveAll(Iterable<S> entities);

	List<KhuyenMai> findAllById(Iterable<String> ids);

	List<KhuyenMai> findAll(Sort sort);

	Page<KhuyenMai> findAll(Pageable pageable);

	<S extends KhuyenMai> Optional<S> findOne(Example<S> example);

	KhuyenMai save(KhuyenMai entity);

	Page<KhuyenMai> findByTenKMLikeOrderByTenKM(String tenkm, Pageable pageable);

	List<KhuyenMai> findAll();

}
