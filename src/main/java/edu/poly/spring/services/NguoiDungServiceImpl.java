package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.NguoiDung;
import edu.poly.spring.reponsitories.NguoiDungReponsitory;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {
	@Autowired
	private NguoiDungReponsitory nguoidungreponsitory;

	@Override
	public NguoiDung save(NguoiDung entity) {
		return nguoidungreponsitory.save(entity);
	}

	@Override
	public <S extends NguoiDung> Optional<S> findOne(Example<S> example) {
		return nguoidungreponsitory.findOne(example);
	}

	@Override
	public Page<NguoiDung> findAll(Pageable pageable) {
		return nguoidungreponsitory.findAll(pageable);
	}

	@Override
	public List<NguoiDung> findAll() {
		return nguoidungreponsitory.findAll();
	}

	@Override
	public List<NguoiDung> findAll(Sort sort) {
		return nguoidungreponsitory.findAll(sort);
	}

	@Override
	public List<NguoiDung> findAllById(Iterable<Integer> ids) {
		return nguoidungreponsitory.findAllById(ids);
	}

	@Override
	public <S extends NguoiDung> List<S> saveAll(Iterable<S> entities) {
		return nguoidungreponsitory.saveAll(entities);
	}

	@Override
	public Optional<NguoiDung> findById(Integer id) {
		return nguoidungreponsitory.findById(id);
	}

	@Override
	public void flush() {
		nguoidungreponsitory.flush();
	}

	@Override
	public <S extends NguoiDung> S saveAndFlush(S entity) {
		return nguoidungreponsitory.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return nguoidungreponsitory.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<NguoiDung> entities) {
		nguoidungreponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends NguoiDung> Page<S> findAll(Example<S> example, Pageable pageable) {
		return nguoidungreponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		nguoidungreponsitory.deleteAllInBatch();
	}

	@Override
	public NguoiDung getOne(Integer id) {
		return nguoidungreponsitory.getOne(id);
	}

	@Override
	public <S extends NguoiDung> long count(Example<S> example) {
		return nguoidungreponsitory.count(example);
	}

	@Override
	public <S extends NguoiDung> boolean exists(Example<S> example) {
		return nguoidungreponsitory.exists(example);
	}

	@Override
	public <S extends NguoiDung> List<S> findAll(Example<S> example) {
		return nguoidungreponsitory.findAll(example);
	}

	@Override
	public long count() {
		return nguoidungreponsitory.count();
	}

	@Override
	public void deleteById(Integer id) {
		nguoidungreponsitory.deleteById(id);
	}

	@Override
	public <S extends NguoiDung> List<S> findAll(Example<S> example, Sort sort) {
		return nguoidungreponsitory.findAll(example, sort);
	}

	@Override
	public void delete(NguoiDung entity) {
		nguoidungreponsitory.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends NguoiDung> entities) {
		nguoidungreponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		nguoidungreponsitory.deleteAll();
	}

	@Override
	public Optional<NguoiDung> findByEmailIgnoreCase(String email) {
		return nguoidungreponsitory.findByEmailIgnoreCase(email);
	}

	@Override
	public Page<NguoiDung> findByTenNDLikeOrderByTenND(String tennd, Pageable pageable) {
		return nguoidungreponsitory.findByTenNDLikeOrderByTenND(tennd, pageable);
	}

	@Override
	public NguoiDung findByEmail(String email) {
		return nguoidungreponsitory.findByEmail(email);
	}

	@Override
	public NguoiDung findByEmailAndMatkhau(String email, String password) {
		return nguoidungreponsitory.findByEmailAndMatkhau(email, password);
	}

	@Override
	public long countByTrangthai(boolean trangthai) {
		return nguoidungreponsitory.countByTrangthai(trangthai);
	}

	@Override
	public int updateNguoiDungSetMatKhauForMaND(String matkhau, Integer maND) {
		return nguoidungreponsitory.updateNguoiDungSetMatKhauForMaND(matkhau, maND);
	}

	@Override
	public Page<Object[]> findNguoidungmuanhieunhat(Boolean trangthai, Pageable pageable) {
		return nguoidungreponsitory.findNguoidungmuanhieunhat(trangthai, pageable);
	}

	

	

	
	

}
