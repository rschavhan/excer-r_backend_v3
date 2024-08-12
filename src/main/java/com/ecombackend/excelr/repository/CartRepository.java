package com.ecombackend.excelr.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecombackend.excelr.model.Cart;
import com.ecombackend.excelr.model.Product;
import com.ecombackend.excelr.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByUserId(Long userId);

	Cart findByUserAndProduct(User user, Product product);

	void deleteByProductId(Long productId);

	void deleteByUserId(Long userId);
	
}
