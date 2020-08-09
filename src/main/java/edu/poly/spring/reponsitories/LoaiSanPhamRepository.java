package edu.poly.spring.reponsitories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.LoaiSanPham;

@Repository
public interface LoaiSanPhamRepository extends JpaRepository<LoaiSanPham, String> {
Page<LoaiSanPham> findByTenLSPLikeOrderByTenLSP(String tenLSP,Pageable pageable);

}
