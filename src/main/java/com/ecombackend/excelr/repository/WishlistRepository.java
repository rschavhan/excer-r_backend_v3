package com.ecombackend.excelr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecombackend.excelr.model.Product;
import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
//    Optional<Wishlist> findByUserId(Long userId);
    List<Wishlist> findByUser(User user);
    void deleteByUserAndProduct(User user, Product product);
}
