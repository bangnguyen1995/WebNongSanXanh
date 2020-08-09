package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.HinhAnhSP;
import edu.poly.spring.reponsitories.AnhSanPhamReponsitory;

@Service
public class HinhAnhSPServiceImpl implements HinhAnhSPService {
@Autowired
 private AnhSanPhamReponsitory anhsanphamreponsitory;


@Override
public Page<HinhAnhSP> findBySanphamMaSP(Integer masp, Pageable pageable) {
	return anhsanphamreponsitory.findBySanphamMaSP(masp, pageable);
}

@Override
public HinhAnhSP save(HinhAnhSP entity) {
	return anhsanphamreponsitory.save(entity);
}

@Override
public <S extends HinhAnhSP> Optional<S> findOne(Example<S> example) {
	return anhsanphamreponsitory.findOne(example);
}

@Override
public Page<HinhAnhSP> findAll(Pageable pageable) {
	return anhsanphamreponsitory.findAll(pageable);
}

@Override
public List<HinhAnhSP> findAll() {
	return anhsanphamreponsitory.findAll();
}

@Override
public List<HinhAnhSP> findAll(Sort sort) {
	return anhsanphamreponsitory.findAll(sort);
}

@Override
public List<HinhAnhSP> findAllById(Iterable<Integer> ids) {
	return anhsanphamreponsitory.findAllById(ids);
}

@Override
public <S extends HinhAnhSP> List<S> saveAll(Iterable<S> entities) {
	return anhsanphamreponsitory.saveAll(entities);
}

@Override
public Optional<HinhAnhSP> findById(Integer id) {
	return anhsanphamreponsitory.findById(id);
}

@Override
public void flush() {
	anhsanphamreponsitory.flush();
}

@Override
public <S extends HinhAnhSP> S saveAndFlush(S entity) {
	return anhsanphamreponsitory.saveAndFlush(entity);
}

@Override
public boolean existsById(Integer id) {
	return anhsanphamreponsitory.existsById(id);
}

@Override
public void deleteInBatch(Iterable<HinhAnhSP> entities) {
	anhsanphamreponsitory.deleteInBatch(entities);
}

@Override
public <S extends HinhAnhSP> Page<S> findAll(Example<S> example, Pageable pageable) {
	return anhsanphamreponsitory.findAll(example, pageable);
}

@Override
public void deleteAllInBatch() {
	anhsanphamreponsitory.deleteAllInBatch();
}

@Override
public HinhAnhSP getOne(Integer id) {
	return anhsanphamreponsitory.getOne(id);
}

@Override
public <S extends HinhAnhSP> long count(Example<S> example) {
	return anhsanphamreponsitory.count(example);
}

@Override
public <S extends HinhAnhSP> boolean exists(Example<S> example) {
	return anhsanphamreponsitory.exists(example);
}

@Override
public <S extends HinhAnhSP> List<S> findAll(Example<S> example) {
	return anhsanphamreponsitory.findAll(example);
}

@Override
public long count() {
	return anhsanphamreponsitory.count();
}

@Override
public void deleteById(Integer id) {
	anhsanphamreponsitory.deleteById(id);
}

@Override
public <S extends HinhAnhSP> List<S> findAll(Example<S> example, Sort sort) {
	return anhsanphamreponsitory.findAll(example, sort);
}

@Override
public void delete(HinhAnhSP entity) {
	anhsanphamreponsitory.delete(entity);
}

@Override
public void deleteAll(Iterable<? extends HinhAnhSP> entities) {
	anhsanphamreponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	anhsanphamreponsitory.deleteAll();
}

@Override
public List<HinhAnhSP> findHinhAnhSanPhamByMaSP(Integer masp) {
	return anhsanphamreponsitory.findHinhAnhSanPhamByMaSP(masp);
}

}
