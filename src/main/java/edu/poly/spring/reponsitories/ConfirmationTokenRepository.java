package edu.poly.spring.reponsitories;

import org.springframework.data.repository.CrudRepository;

import edu.poly.spring.models.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String>{
	ConfirmationToken findByConfirmationToken(String confirmationToken);

	ConfirmationToken findBynguoidungMaND(Integer mand);
}
