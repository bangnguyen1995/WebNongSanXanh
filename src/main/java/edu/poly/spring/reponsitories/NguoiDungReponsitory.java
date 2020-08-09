package edu.poly.spring.reponsitories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.NguoiDung;

@Repository
public interface NguoiDungReponsitory extends JpaRepository<NguoiDung, Integer> {
	Page<NguoiDung> findByTenNDLikeOrderByTenND(String tennd, Pageable pageable);

	Optional<NguoiDung> findByEmailIgnoreCase(String email);

	NguoiDung findByEmail(String email);

	NguoiDung findByEmailAndMatkhau(String email, String password);

	long countByTrangthai(boolean trangthai);

	@Modifying
	@Transactional
	@Query("UPDATE NguoiDung nd SET nd.matkhau = ?1 WHERE nd.maND = ?2")
	int updateNguoiDungSetMatKhauForMaND(String matkhau, Integer maND);
	@Query(value = "SELECT nd.mand, nd.tennd, nd.email, nd.sdt, nd.gioitinh, SUM(hd.tongtien) AS 'tiền mua' FROM nguoidung nd  join hoadon hd on nd.mand = hd.mand "
			+ "where nd.trangthai=?1 "
			+ "group by nd.mand, nd.tennd, nd.diachi, nd.email, nd.sdt, nd.gioitinh,nd.trangthai, nd.ngaysinh, nd.matkhau "
			+ "order by SUM(hd.tongtien) DESC", nativeQuery = true)
	Page<Object[]> findNguoidungmuanhieunhat( Boolean trangthai, Pageable pageable);
}
