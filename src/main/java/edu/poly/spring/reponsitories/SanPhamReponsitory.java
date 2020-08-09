package edu.poly.spring.reponsitories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.SanPham;

@Repository
public interface SanPhamReponsitory extends JpaRepository<SanPham, Integer> {
	@Query(value = "SELECT s.masp, s.giasp, s.tensp, SUM(hd.soluongmua) AS 'số lượng bán' FROM sanpham s join hoadonchitiet hd on s.masp = hd.masp  join hoadon d on hd.mahd = d.mahd  "
			+ "where d.ngaydat between ?1 and ?2 "
			+ "group by s.masp, s.giasp, s.tensp, s.anhsp, s.makm ,s.malsp, s.mota, s.ngayhethan, s.ngaynhap, s.trangthai, s.soluong "
			+ "order by SUM(hd.soluongmua) DESC", nativeQuery = true)
	Page<Object[]> findSanphamBannhieunhat(String ngaydat1, String ngaydat2, Pageable pageable);

	@Query("SELECT s FROM SanPham s JOIN LoaiSanPham l on s.loaisanpham = l.maLSP WHERE l.maLSP = ?1")
	Page<SanPham> findSanPhamByMaLSP(String malsp, Pageable pageable);

	@Query(value = "SELECT TOP 10 * FROM SanPham s JOIN KhuyenMai k on s.maKM = k.maKM WHERE k.GiamGia > 0", nativeQuery = true)
	List<SanPham> findTop10SanPhamByMaMK();

	@Query(value = "SELECT * FROM SanPham s JOIN KhuyenMai k on s.maKM = k.maKM WHERE k.GiamGia > 0", nativeQuery = true)
	Page<SanPham> findSanPhamByMaMK(Pageable pageable);

	@Query(value = "SELECT TOP 12 s.* FROM sanpham s join hoadonchitiet hd on s.masp = hd.masp "
			+ "group by s.masp, s.giasp, s.tensp, s.anhsp, s.makm ,s.malsp, s.mota, s.ngayhethan, s.ngaynhap, s.trangthai, s.soluong "
			+ "order by SUM(hd.soluongmua) DESC", nativeQuery = true)
	List<SanPham> findTop12SanPhamBySUMSoLuong();

	@Query(value = "SELECT TOP 10 s.* FROM sanpham s ORDER BY s.ngaynhap DESC", nativeQuery = true)
	List<SanPham> findTop10SanPhamOrderByNgayNhap();

	@Query(value = "SELECT TOP 10 s.* FROM sanpham s JOIN danhgia dg ON s.masp = dg.masp "
			+ "GROUP BY s.masp, s.giasp, s.tensp, s.anhsp, s.makm ,s.malsp, s.mota, s.ngayhethan, s.ngaynhap, s.trangthai, s.soluong "
			+ "ORDER BY AVG(dg.rating) DESC", nativeQuery = true)
	List<SanPham> findTop10SanPhamOrderByDanhGia();

	@Query(value = "SELECT Top 10 * FROM SanPham s JOIN LoaiSanPham l on s.maLSP = l.maLSP WHERE l.maLSP = ?1 and  s.masp not in ( ?2 )", nativeQuery = true)
	List<SanPham> findTop10SanPhamByMaLSP(String malsp, Integer masp);

	Page<SanPham> findByTenSPLikeOrderByTenSP(String tensp, Pageable pageable);

	Page<SanPham> findBySoluongLessThan(int soluong, Pageable pageable);

	List<SanPham> findByLoaisanphamMaLSP(String malsp);

	
}
