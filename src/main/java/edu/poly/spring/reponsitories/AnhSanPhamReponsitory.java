package edu.poly.spring.reponsitories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.HinhAnhSP;
import edu.poly.spring.models.SanPham;

@Repository
public interface AnhSanPhamReponsitory extends JpaRepository<HinhAnhSP, Integer> {

	Page<HinhAnhSP> findBySanphamMaSP(Integer masp, Pageable pageable);

	@Query(value = "Select Top 4 * FROM hinhanhsp ha WHERE ha.masp = ?1", nativeQuery = true)
	List<HinhAnhSP> findHinhAnhSanPhamByMaSP(Integer masp);

}
