package com.ecombackend.excelr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecombackend.excelr.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    List<Order> findByUserId(Long userId);

    Optional<Order> findByIdAndUserId(Long orderId, Long userId);
}

