package edu.poly.spring.services;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import edu.poly.spring.models.KhuyenMai;
import edu.poly.spring.models.LoaiSanPham;
import edu.poly.spring.models.SanPham;
import edu.poly.spring.reponsitories.KhuyenMaiReponsitory;

import edu.poly.spring.reponsitories.LoaiSanPhamRepository;
import edu.poly.spring.reponsitories.SanPhamReponsitory;

@Service
public class SanPhamServiceImpl implements SanPhamService {
@Autowired
private SanPhamReponsitory sanphamreponsitory;

@Override
public SanPham save(SanPham entity) {
	return sanphamreponsitory.save(entity);
}

@Override
public <S extends SanPham> Iterable<S> saveAll(Iterable<S> entities) {
	return sanphamreponsitory.saveAll(entities);
}

@Override
public Optional<SanPham> findById(Integer id) {
	return sanphamreponsitory.findById(id);
}

@Override
public boolean existsById(Integer id) {
	return sanphamreponsitory.existsById(id);
}

@Override
public Page<SanPham> findAll(Pageable pageable) {
	return (Page<SanPham>) sanphamreponsitory.findAll(pageable);
}

@Override
public Iterable<SanPham> findAllById(Iterable<Integer> ids) {
	return sanphamreponsitory.findAllById(ids);
}

@Override
public long count() {
	return sanphamreponsitory.count();
}

@Override
public void deleteById(Integer id) {
	sanphamreponsitory.deleteById(id);
}

@Override
public void delete(SanPham entity) {
	sanphamreponsitory.delete(entity);
}

@Override
public void deleteAll(Iterable<? extends SanPham> entities) {
	sanphamreponsitory.deleteAll(entities);
}

@Override
public void deleteAll() {
	sanphamreponsitory.deleteAll();
}

@Override
public Page<SanPham> findByTenSPLikeOrderByTenSP(String tensp, Pageable pageable) {
	return  (Page<SanPham>)sanphamreponsitory.findByTenSPLikeOrderByTenSP(tensp, pageable);
}

@Override
public List<SanPham> findAll() {
	return sanphamreponsitory.findAll();
}

@Override
public Page<SanPham> findBySoluongLessThan(int soluong, Pageable pageable) {
	return sanphamreponsitory.findBySoluongLessThan(soluong, pageable);
}

@Override
public Page<SanPham> findSanPhamByMaLSP(String malsp, Pageable pageable) {
	return sanphamreponsitory.findSanPhamByMaLSP(malsp, pageable);
}

@Override
public List<SanPham> findTop10SanPhamByMaMK() {
	return sanphamreponsitory.findTop10SanPhamByMaMK();
}

@Override
public Page<SanPham> findSanPhamByMaMK(Pageable pageable) {
	return sanphamreponsitory.findSanPhamByMaMK(pageable);
}

@Override
public List<SanPham> findTop12SanPhamBySUMSoLuong() {
	return sanphamreponsitory.findTop12SanPhamBySUMSoLuong();
}

@Override
public List<SanPham> findTop10SanPhamOrderByNgayNhap() {
	return sanphamreponsitory.findTop10SanPhamOrderByNgayNhap();
}

@Override
public List<SanPham> findTop10SanPhamOrderByDanhGia() {
	return sanphamreponsitory.findTop10SanPhamOrderByDanhGia();
}



@Override
public List<SanPham> findTop10SanPhamByMaLSP(String malsp, Integer masp) {
	return sanphamreponsitory.findTop10SanPhamByMaLSP(malsp, masp);
}

@Override
public List<SanPham> findByLoaisanphamMaLSP(String malsp) {
	return sanphamreponsitory.findByLoaisanphamMaLSP(malsp);
}

@Override
public Page<Object[]> findSanphamBannhieunhat(String ngaydat1, String ngaydat2, Pageable pageable) {
	return sanphamreponsitory.findSanphamBannhieunhat(ngaydat1, ngaydat2, pageable);
}







}
