package com.ecombackend.excelr.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecombackend.excelr.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByName(String name);
}
