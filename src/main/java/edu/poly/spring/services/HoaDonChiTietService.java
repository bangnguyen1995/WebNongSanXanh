package edu.poly.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.HoaDonChiTiet;

public interface HoaDonChiTietService {

	void deleteAll();

	void deleteAll(Iterable<? extends HoaDonChiTiet> entities);

	void delete(HoaDonChiTiet entity);

	<S extends HoaDonChiTiet> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends HoaDonChiTiet> List<S> findAll(Example<S> example);

	<S extends HoaDonChiTiet> boolean exists(Example<S> example);

	<S extends HoaDonChiTiet> long count(Example<S> example);

	HoaDonChiTiet getOne(Integer id);

	void deleteAllInBatch();

	<S extends HoaDonChiTiet> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<HoaDonChiTiet> entities);

	boolean existsById(Integer id);

	<S extends HoaDonChiTiet> S saveAndFlush(S entity);

	void flush();

	Optional<HoaDonChiTiet> findById(Integer id);

	<S extends HoaDonChiTiet> List<S> saveAll(Iterable<S> entities);

	List<HoaDonChiTiet> findAllById(Iterable<Integer> ids);

	List<HoaDonChiTiet> findAll(Sort sort);

	List<HoaDonChiTiet> findAll();

	Page<HoaDonChiTiet> findAll(Pageable pageable);

	<S extends HoaDonChiTiet> Optional<S> findOne(Example<S> example);

	HoaDonChiTiet save(HoaDonChiTiet entity);

	List<HoaDonChiTiet> findByHoadonNgaydat(Date ngaydat);

	List<HoaDonChiTiet> findByHoadonMaHD(Integer mahd);

}
