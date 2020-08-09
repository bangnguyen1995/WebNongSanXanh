package edu.poly.spring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.HoaDonChiTiet;
import edu.poly.spring.reponsitories.HoaDonChiTietReponsitory;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
@Autowired 
private HoaDonChiTietReponsitory hoadonchitietreponsitory;

@Override
public   HoaDonChiTiet save( HoaDonChiTiet entity) {
	return hoadonchitietreponsitory.save(entity);
}

@Override
public <S extends HoaDonChiTiet> Optional<S> findOne(Example<S> example) {
	return hoadonchitietreponsitory.findOne(example);
}

@Override
public Page<HoaDonChiTiet> findAll(Pageable pageable) {
	return hoadonchitietreponsitory.findAll(pageable);
}

@Override
public List<HoaDonChiTiet> findAll() {
	return hoadonchitietreponsitory.findAll();
}

@Override
public List<HoaDonChiTiet> findAll(Sort sort) {
	return hoadonchitietreponsitory.findAll(sort);
}

@Override
public List<HoaDonChiTiet> findAllById(Iterable<Integer> ids) {
	return hoadonchitietreponsitory.findAllById(ids);
}

@Override
public <S extends HoaDonChiTiet> List<S> saveAll(Iterable<S> entities) {
	return hoadonchitietreponsitory.saveAll(entities);
}

@Override
public Optional<HoaDonChiTiet> findById(Integer id) {
	return hoadonchitietreponsitory.findById(id);
}

@Override
public void flush() {
	hoadonchitietreponsitory.flush();
}

@Override
public <S extends HoaDonChiTiet> S saveAndFlush(S entity) {
	return hoadonchitietreponsitory.saveAndFlush(entity);
}

@Override
public boolean existsById(Integer id) {
	return hoadonchitietreponsitory.existsById(id);
}

@Override
public void deleteInBatch(Iterable<HoaDonChiTiet> entities) {
	hoadonchitietreponsitory.deleteInBatch(entities);
}

@Override
public <S extends HoaDonChiTiet> Page<S> findAll(Example<S> example, Pageable pageable) {
	return hoadonchitietreponsitory.findAll(example, pageable);
}

@Override
public void deleteAllInBatch() {
	hoadonchitietreponsitory.deleteAllInBatch();
}

@Override
public HoaDonChiTiet getOne(Integer id) {
	return hoadonchitietreponsitory.getOne(id);
}

@Override
public <S extends HoaDonChiTiet> long count(Example<S> example) {
	return hoadonchitietreponsitory.count(example);
}

@Override
public <S extends HoaDonChiTiet> boolean exists(Example<S> example) {
	return hoadonchitietreponsitory.exists(example);
}

@Override
public <S extends HoaDonChiTiet> List<S> findAll(Example<S> example) {
	return hoadonchitietreponsitory.findAll(example);
}

@Override
public long count() {
	return hoadonchitietreponsitory.count();
}

@Override
public void deleteById(Integer id) {
	hoadonchitietreponsitory.deleteById(id);
}

@Override
public <S extends HoaDonChiTiet> List<S> findAll(Example<S> example, Sort sort) {
	return hoadonchitietreponsitory.findAll(example, sort);
}

@Override
public void delete(HoaDonChiTiet entity) {
	hoadonchitietreponsitory.delete(entity);
}

@Override
public void deleteAll(Iterable<? extends HoaDonChiTiet> entities) {
	hoadonchitietreponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	hoadonchitietreponsitory.deleteAll();
}

@Override
public List<HoaDonChiTiet> findByHoadonNgaydat(Date ngaydat) {
	return hoadonchitietreponsitory.findByHoadonNgaydat(ngaydat);
}

@Override
public List<HoaDonChiTiet> findByHoadonMaHD(Integer mahd) {
	return hoadonchitietreponsitory.findByHoadonMaHD(mahd);
}


}
