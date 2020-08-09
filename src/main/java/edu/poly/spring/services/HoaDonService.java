package edu.poly.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.HoaDon;

public interface HoaDonService {

	void deleteAll();

	void deleteAll(Iterable<? extends HoaDon> entities);

	void delete(HoaDon entity);

	<S extends HoaDon> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends HoaDon> List<S> findAll(Example<S> example);

	<S extends HoaDon> boolean exists(Example<S> example);

	<S extends HoaDon> long count(Example<S> example);

	HoaDon getOne(Integer id);

	void deleteAllInBatch();

	<S extends HoaDon> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<HoaDon> entities);

	boolean existsById(Integer id);

	<S extends HoaDon> S saveAndFlush(S entity);

	void flush();

	Optional<HoaDon> findById(Integer id);

	<S extends HoaDon> List<S> saveAll(Iterable<S> entities);

	List<HoaDon> findAllById(Iterable<Integer> ids);

	List<HoaDon> findAll(Sort sort);

	List<HoaDon> findAll();

	Page<HoaDon> findAll(Pageable pageable);

	<S extends HoaDon> Optional<S> findOne(Example<S> example);

	HoaDon save(HoaDon  entity);

	Page<HoaDon> findByNguoidungMaNDOrderByNgaydatDesc(int maND, Pageable pageable);

	long countByNgaydat(Date ngaydat);

	Page<HoaDon> findByTrangthai(boolean trangthai, Pageable pageable);

	Page<HoaDon> findByNguoidungTenNDLikeAndNgaydatAndTrangthai(String tennd, Date ngaydat, Boolean trangthai, Pageable pageable);

	Page<HoaDon> findByNgaydatBetweenAndTrangthai(Date ngaydat, Date ngaydat2, boolean trangthai, Pageable pageable);



}
