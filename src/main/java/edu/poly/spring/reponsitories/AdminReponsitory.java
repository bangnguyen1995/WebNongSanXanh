package edu.poly.spring.reponsitories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.spring.models.Admin;
import edu.poly.spring.models.NguoiDung;

@Repository
public interface AdminReponsitory extends JpaRepository<Admin, String> {
	Optional<Admin> findByUsernameIgnoreCase(String username);
	
	Optional <Admin> findByUsernameAndPassword(String username, String password);
}
