package edu.poly.spring.reponsitories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.KhuyenMai;

@Repository
public interface KhuyenMaiReponsitory extends JpaRepository<KhuyenMai, String> {
	List<KhuyenMai> findAll();
	Page<KhuyenMai> findByTenKMLikeOrderByTenKM(String tenkm, Pageable pageable);
}
