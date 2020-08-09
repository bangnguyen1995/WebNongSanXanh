package edu.poly.spring.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.PhuongThucThanhToan;

@Repository
public interface PhuongThucThanhToanReponsitory extends JpaRepository<PhuongThucThanhToan, Integer> {

}
