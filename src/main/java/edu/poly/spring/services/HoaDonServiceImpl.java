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

import edu.poly.spring.models.HoaDon;
import edu.poly.spring.reponsitories.HoaDonReponsitory;

@Service
public class HoaDonServiceImpl implements HoaDonService {
@Autowired
private HoaDonReponsitory hoadonreponsitory;

@Override
public  HoaDon save(HoaDon  entity) {
	return hoadonreponsitory.save(entity);
}

@Override
public <S extends HoaDon> Optional<S> findOne(Example<S> example) {
	return hoadonreponsitory.findOne(example);
}

@Override
public Page<HoaDon> findAll(Pageable pageable) {
	return hoadonreponsitory.findAll(pageable);
}

@Override
public List<HoaDon> findAll() {
	return hoadonreponsitory.findAll();
}

@Override
public List<HoaDon> findAll(Sort sort) {
	return hoadonreponsitory.findAll(sort);
}

@Override
public List<HoaDon> findAllById(Iterable<Integer> ids) {
	return hoadonreponsitory.findAllById(ids);
}

@Override
public <S extends HoaDon> List<S> saveAll(Iterable<S> entities) {
	return hoadonreponsitory.saveAll(entities);
}

@Override
public Optional<HoaDon> findById(Integer id) {
	return hoadonreponsitory.findById(id);
}

@Override
public void flush() {
	hoadonreponsitory.flush();
}

@Override
public <S extends HoaDon> S saveAndFlush(S entity) {
	return hoadonreponsitory.saveAndFlush(entity);
}

@Override
public boolean existsById(Integer id) {
	return hoadonreponsitory.existsById(id);
}

@Override
public void deleteInBatch(Iterable<HoaDon> entities) {
	hoadonreponsitory.deleteInBatch(entities);
}

@Override
public <S extends HoaDon> Page<S> findAll(Example<S> example, Pageable pageable) {
	return hoadonreponsitory.findAll(example, pageable);
}

@Override
public void deleteAllInBatch() {
	hoadonreponsitory.deleteAllInBatch();
}

@Override
public HoaDon getOne(Integer id) {
	return hoadonreponsitory.getOne(id);
}

@Override
public <S extends HoaDon> long count(Example<S> example) {
	return hoadonreponsitory.count(example);
}

@Override
public <S extends HoaDon> boolean exists(Example<S> example) {
	return hoadonreponsitory.exists(example);
}

@Override
public <S extends HoaDon> List<S> findAll(Example<S> example) {
	return hoadonreponsitory.findAll(example);
}

@Override
public long count() {
	return hoadonreponsitory.count();
}

@Override
public void deleteById(Integer id) {
	hoadonreponsitory.deleteById(id);
}

@Override
public <S extends HoaDon> List<S> findAll(Example<S> example, Sort sort) {
	return hoadonreponsitory.findAll(example, sort);
}

@Override
public void delete(HoaDon entity) {
	hoadonreponsitory.delete(entity);
}

@Override
public void deleteAll(Iterable<? extends HoaDon> entities) {
	hoadonreponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	hoadonreponsitory.deleteAll();
}

@Override
public Page<HoaDon> findByNguoidungMaNDOrderByNgaydatDesc(int maND, Pageable pageable) {
	return hoadonreponsitory.findByNguoidungMaNDOrderByNgaydatDesc(maND, pageable);
}

@Override
public long countByNgaydat(Date ngaydat) {
	return hoadonreponsitory.countByNgaydat(ngaydat);
}

@Override
public Page<HoaDon> findByTrangthai(boolean trangthai, Pageable pageable) {
	return hoadonreponsitory.findByTrangthai(trangthai, pageable);
}

@Override
public Page<HoaDon> findByNguoidungTenNDLikeAndNgaydatAndTrangthai(String tennd, Date ngaydat, Boolean trangthai,
		Pageable pageable) {
	return hoadonreponsitory.findByNguoidungTenNDLikeAndNgaydatAndTrangthai(tennd, ngaydat, trangthai, pageable);
}

@Override
public Page<HoaDon> findByNgaydatBetweenAndTrangthai(Date ngaydat, Date ngaydat2, boolean trangthai,
		Pageable pageable) {
	return hoadonreponsitory.findByNgaydatBetweenAndTrangthai(ngaydat, ngaydat2, trangthai, pageable);
}




}
