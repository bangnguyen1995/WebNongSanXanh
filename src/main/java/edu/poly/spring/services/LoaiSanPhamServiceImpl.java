package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.reponsitories.LoaiSanPhamRepository;

@Service
public class LoaiSanPhamServiceImpl implements LoaiSanPhamService {
@Autowired
private LoaiSanPhamRepository loaisanpamreponsitory;

@Override
public  LoaiSanPham save (LoaiSanPham entity) {
	return loaisanpamreponsitory.save(entity);
}

@Override
public <S extends LoaiSanPham> Optional<S> findOne(Example<S> example) {
	return loaisanpamreponsitory.findOne(example);
}

@Override
public Page<LoaiSanPham> findAll(Pageable pageable) {
	return loaisanpamreponsitory.findAll(pageable);
}

@Override
public List<LoaiSanPham> findAll() {
	return loaisanpamreponsitory.findAll();
}

@Override
public List<LoaiSanPham> findAll(Sort sort) {
	return loaisanpamreponsitory.findAll(sort);
}

@Override
public List<LoaiSanPham> findAllById(Iterable<String> ids) {
	return loaisanpamreponsitory.findAllById(ids);
}

@Override
public <S extends LoaiSanPham> List<S> saveAll(Iterable<S> entities) {
	return loaisanpamreponsitory.saveAll(entities);
}

@Override
public Optional<LoaiSanPham> findById(String id) {
	return loaisanpamreponsitory.findById(id);
}

@Override
public void flush() {
	loaisanpamreponsitory.flush();
}

@Override
public <S extends LoaiSanPham> S saveAndFlush(S entity) {
	return loaisanpamreponsitory.saveAndFlush(entity);
}

@Override
public boolean existsById(String id) {
	return loaisanpamreponsitory.existsById(id);
}

@Override
public void deleteInBatch(Iterable<LoaiSanPham> entities) {
	loaisanpamreponsitory.deleteInBatch(entities);
}

@Override
public <S extends LoaiSanPham> Page<S> findAll(Example<S> example, Pageable pageable) {
	return loaisanpamreponsitory.findAll(example, pageable);
}

@Override
public void deleteAllInBatch() {
	loaisanpamreponsitory.deleteAllInBatch();
}

@Override
public LoaiSanPham getOne(String id) {
	return loaisanpamreponsitory.getOne(id);
}

@Override
public <S extends LoaiSanPham> long count(Example<S> example) {
	return loaisanpamreponsitory.count(example);
}

@Override
public <S extends LoaiSanPham> boolean exists(Example<S> example) {
	return loaisanpamreponsitory.exists(example);
}

@Override
public <S extends LoaiSanPham> List<S> findAll(Example<S> example) {
	return loaisanpamreponsitory.findAll(example);
}

@Override
public long count() {
	return loaisanpamreponsitory.count();
}

@Override
public void deleteById(String id) {
	loaisanpamreponsitory.deleteById(id);
}

@Override
public <S extends LoaiSanPham> List<S> findAll(Example<S> example, Sort sort) {
	return loaisanpamreponsitory.findAll(example, sort);
}

@Override
public void delete(LoaiSanPham entity) {
	loaisanpamreponsitory.delete(entity);
}

@Override
public void deleteAll(Iterable<? extends LoaiSanPham> entities) {
	loaisanpamreponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	loaisanpamreponsitory.deleteAll();
}

@Override
public Page<LoaiSanPham> findByTenLSPLikeOrderByTenLSP(String tenLSP, Pageable pageable) {
	return loaisanpamreponsitory.findByTenLSPLikeOrderByTenLSP(tenLSP, pageable);
}

}
