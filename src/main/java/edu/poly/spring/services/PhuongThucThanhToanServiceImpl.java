package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.PhuongThucThanhToan;
import edu.poly.spring.reponsitories.PhuongThucThanhToanReponsitory;

@Service
public class PhuongThucThanhToanServiceImpl implements PhuongThucThanhToanService {
	@Autowired
	private PhuongThucThanhToanReponsitory phuongthucthanhtoanreponsitory;

	@Override
	public PhuongThucThanhToan save(PhuongThucThanhToan entity) {
		return phuongthucthanhtoanreponsitory.save(entity);
	}

	@Override
	public <S extends PhuongThucThanhToan> Optional<S> findOne(Example<S> example) {
		return phuongthucthanhtoanreponsitory.findOne(example);
	}

	@Override
	public Page<PhuongThucThanhToan> findAll(Pageable pageable) {
		return phuongthucthanhtoanreponsitory.findAll(pageable);
	}

	@Override
	public List<PhuongThucThanhToan> findAll() {
		return phuongthucthanhtoanreponsitory.findAll();
	}

	@Override
	public List<PhuongThucThanhToan> findAll(Sort sort) {
		return phuongthucthanhtoanreponsitory.findAll(sort);
	}

	@Override
	public List<PhuongThucThanhToan> findAllById(Iterable<Integer> ids) {
		return phuongthucthanhtoanreponsitory.findAllById(ids);
	}

	@Override
	public <S extends PhuongThucThanhToan> List<S> saveAll(Iterable<S> entities) {
		return phuongthucthanhtoanreponsitory.saveAll(entities);
	}

	@Override
	public Optional<PhuongThucThanhToan> findById(Integer id) {
		return phuongthucthanhtoanreponsitory.findById(id);
	}

	@Override
	public void flush() {
		phuongthucthanhtoanreponsitory.flush();
	}

	@Override
	public <S extends PhuongThucThanhToan> S saveAndFlush(S entity) {
		return phuongthucthanhtoanreponsitory.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return phuongthucthanhtoanreponsitory.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<PhuongThucThanhToan> entities) {
		phuongthucthanhtoanreponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends PhuongThucThanhToan> Page<S> findAll(Example<S> example, Pageable pageable) {
		return phuongthucthanhtoanreponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		phuongthucthanhtoanreponsitory.deleteAllInBatch();
	}

	@Override
	public PhuongThucThanhToan getOne(Integer id) {
		return phuongthucthanhtoanreponsitory.getOne(id);
	}

	@Override
	public <S extends PhuongThucThanhToan> long count(Example<S> example) {
		return phuongthucthanhtoanreponsitory.count(example);
	}

	@Override
	public <S extends PhuongThucThanhToan> boolean exists(Example<S> example) {
		return phuongthucthanhtoanreponsitory.exists(example);
	}

	@Override
	public <S extends PhuongThucThanhToan> List<S> findAll(Example<S> example) {
		return phuongthucthanhtoanreponsitory.findAll(example);
	}

	@Override
	public long count() {
		return phuongthucthanhtoanreponsitory.count();
	}

	@Override
	public void deleteById(Integer id) {
		phuongthucthanhtoanreponsitory.deleteById(id);
	}

	@Override
	public <S extends PhuongThucThanhToan> List<S> findAll(Example<S> example, Sort sort) {
		return phuongthucthanhtoanreponsitory.findAll(example, sort);
	}

	@Override
	public void delete(PhuongThucThanhToan entity) {
		phuongthucthanhtoanreponsitory.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends PhuongThucThanhToan> entities) {
		phuongthucthanhtoanreponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		phuongthucthanhtoanreponsitory.deleteAll();
	}

}
