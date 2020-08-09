package edu.poly.spring.reponsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.DanhGia;

@Repository
public interface DanhGiaReponsitory extends JpaRepository<DanhGia, Integer> {

}
