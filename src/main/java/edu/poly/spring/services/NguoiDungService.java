package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.spring.models.NguoiDung;

public interface NguoiDungService {

	Optional<NguoiDung> findByEmailIgnoreCase(String email);

	void deleteAll();

	void deleteAll(Iterable<? extends NguoiDung> entities);

	void delete(NguoiDung entity);

	<S extends NguoiDung> List<S> findAll(Example<S> example, Sort sort);

	void deleteById(Integer id);

	long count();

	<S extends NguoiDung> List<S> findAll(Example<S> example);

	<S extends NguoiDung> boolean exists(Example<S> example);

	<S extends NguoiDung> long count(Example<S> example);

	NguoiDung getOne(Integer id);

	void deleteAllInBatch();

	<S extends NguoiDung> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<NguoiDung> entities);

	boolean existsById(Integer id);

	<S extends NguoiDung> S saveAndFlush(S entity);

	void flush();

	Optional<NguoiDung> findById(Integer id);

	<S extends NguoiDung> List<S> saveAll(Iterable<S> entities);

	List<NguoiDung> findAllById(Iterable<Integer> ids);

	List<NguoiDung> findAll(Sort sort);

	List<NguoiDung> findAll();

	Page<NguoiDung> findAll(Pageable pageable);

	<S extends NguoiDung> Optional<S> findOne(Example<S> example);

	NguoiDung save(NguoiDung entity);

	Page<NguoiDung> findByTenNDLikeOrderByTenND(String tennd, Pageable pageable);

	NguoiDung findByEmailAndMatkhau(String email, String password);

	NguoiDung findByEmail(String email);

	long countByTrangthai(boolean trangthai);

	int updateNguoiDungSetMatKhauForMaND(String matkhau, Integer maND);

	Page<Object[]> findNguoidungmuanhieunhat(Boolean trangthai, Pageable pageable);

	



	

	

}
