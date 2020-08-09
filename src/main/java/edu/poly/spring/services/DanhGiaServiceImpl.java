package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.DanhGia;
import edu.poly.spring.reponsitories.DanhGiaReponsitory;

@Service
public class DanhGiaServiceImpl implements DanhGiaService {
	@Autowired
	private DanhGiaReponsitory danhGiaRepository;

	@Override
	public DanhGia save(DanhGia entity) {
		return danhGiaRepository.save(entity);
	}

	@Override
	public <S extends DanhGia> Optional<S> findOne(Example<S> example) {
		return danhGiaRepository.findOne(example);
	}

	@Override
	public Page<DanhGia> findAll(Pageable pageable) {
		return danhGiaRepository.findAll(pageable);
	}

	@Override
	public List<DanhGia> findAll() {
		return danhGiaRepository.findAll();
	}

	@Override
	public List<DanhGia> findAll(Sort sort) {
		return danhGiaRepository.findAll(sort);
	}

	@Override
	public List<DanhGia> findAllById(Iterable<Integer> ids) {
		return danhGiaRepository.findAllById(ids);
	}

	@Override
	public <S extends DanhGia> List<S> saveAll(Iterable<S> entities) {
		return danhGiaRepository.saveAll(entities);
	}

	@Override
	public Optional<DanhGia> findById(Integer id) {
		return danhGiaRepository.findById(id);
	}

	@Override
	public void flush() {
		danhGiaRepository.flush();
	}

	@Override
	public <S extends DanhGia> S saveAndFlush(S entity) {
		return danhGiaRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return danhGiaRepository.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<DanhGia> entities) {
		danhGiaRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends DanhGia> Page<S> findAll(Example<S> example, Pageable pageable) {
		return danhGiaRepository.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		danhGiaRepository.deleteAllInBatch();
	}

	@Override
	public DanhGia getOne(Integer id) {
		return danhGiaRepository.getOne(id);
	}

	@Override
	public <S extends DanhGia> long count(Example<S> example) {
		return danhGiaRepository.count(example);
	}

	@Override
	public <S extends DanhGia> boolean exists(Example<S> example) {
		return danhGiaRepository.exists(example);
	}

	@Override
	public <S extends DanhGia> List<S> findAll(Example<S> example) {
		return danhGiaRepository.findAll(example);
	}

	@Override
	public long count() {
		return danhGiaRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		danhGiaRepository.deleteById(id);
	}

	@Override
	public <S extends DanhGia> List<S> findAll(Example<S> example, Sort sort) {
		return danhGiaRepository.findAll(example, sort);
	}

	@Override
	public void delete(DanhGia entity) {
		danhGiaRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends DanhGia> entities) {
		danhGiaRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		danhGiaRepository.deleteAll();
	}
	
}
