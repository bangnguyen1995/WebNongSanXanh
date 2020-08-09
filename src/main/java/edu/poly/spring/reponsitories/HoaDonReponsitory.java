package edu.poly.spring.reponsitories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.HoaDon;

@Repository
public interface HoaDonReponsitory extends JpaRepository<HoaDon, Integer> {
	Page<HoaDon> findByNguoidungMaNDOrderByNgaydatDesc(int maND, Pageable pageable);
	long countByNgaydat(Date ngaydat);
	Page<HoaDon> findByTrangthai(boolean trangthai,Pageable pageable);
	Page<HoaDon> findByNguoidungTenNDLikeAndNgaydatAndTrangthai(String tennd, Date ngaydat,Boolean trangthai, Pageable pageable );
	Page<HoaDon> findByNgaydatBetweenAndTrangthai(Date ngaydat,Date ngaydat2, boolean trangthai, Pageable pageable) ;
}
