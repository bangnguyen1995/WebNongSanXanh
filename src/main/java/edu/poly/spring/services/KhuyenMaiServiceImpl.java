package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.KhuyenMai;
import edu.poly.spring.reponsitories.KhuyenMaiReponsitory;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
	@Autowired
	private KhuyenMaiReponsitory khuyenmaiReponsitory;

	@Override
	public List<KhuyenMai> findAll() {
		return khuyenmaiReponsitory.findAll();
	}

	@Override
	public Page<KhuyenMai> findByTenKMLikeOrderByTenKM(String tenkm, Pageable pageable) {
		return khuyenmaiReponsitory.findByTenKMLikeOrderByTenKM(tenkm, pageable);
	}

	@Override
	public  KhuyenMai save(KhuyenMai entity) {
		return khuyenmaiReponsitory.save(entity);
	}

	@Override
	public <S extends KhuyenMai> Optional<S> findOne(Example<S> example) {
		return khuyenmaiReponsitory.findOne(example);
	}

	@Override
	public Page<KhuyenMai> findAll(Pageable pageable) {
		return khuyenmaiReponsitory.findAll(pageable);
	}

	@Override
	public List<KhuyenMai> findAll(Sort sort) {
		return khuyenmaiReponsitory.findAll(sort);
	}

	@Override
	public List<KhuyenMai> findAllById(Iterable<String> ids) {
		return khuyenmaiReponsitory.findAllById(ids);
	}

	@Override
	public <S extends KhuyenMai> List<S> saveAll(Iterable<S> entities) {
		return khuyenmaiReponsitory.saveAll(entities);
	}

	@Override
	public Optional<KhuyenMai> findById(String id) {
		return khuyenmaiReponsitory.findById(id);
	}

	@Override
	public void flush() {
		khuyenmaiReponsitory.flush();
	}

	@Override
	public <S extends KhuyenMai> S saveAndFlush(S entity) {
		return khuyenmaiReponsitory.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return khuyenmaiReponsitory.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<KhuyenMai> entities) {
		khuyenmaiReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends KhuyenMai> Page<S> findAll(Example<S> example, Pageable pageable) {
		return khuyenmaiReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		khuyenmaiReponsitory.deleteAllInBatch();
	}

	@Override
	public KhuyenMai getOne(String id) {
		return khuyenmaiReponsitory.getOne(id);
	}

	@Override
	public <S extends KhuyenMai> long count(Example<S> example) {
		return khuyenmaiReponsitory.count(example);
	}

	@Override
	public <S extends KhuyenMai> boolean exists(Example<S> example) {
		return khuyenmaiReponsitory.exists(example);
	}

	@Override
	public <S extends KhuyenMai> List<S> findAll(Example<S> example) {
		return khuyenmaiReponsitory.findAll(example);
	}

	@Override
	public long count() {
		return khuyenmaiReponsitory.count();
	}

	@Override
	public void deleteById(String id) {
		khuyenmaiReponsitory.deleteById(id);
	}

	@Override
	public <S extends KhuyenMai> List<S> findAll(Example<S> example, Sort sort) {
		return khuyenmaiReponsitory.findAll(example, sort);
	}

	@Override
	public void delete(KhuyenMai entity) {
		khuyenmaiReponsitory.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends KhuyenMai> entities) {
		khuyenmaiReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		khuyenmaiReponsitory.deleteAll();
	}
	
}
