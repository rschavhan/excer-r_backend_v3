package com.ecombackend.excelr.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecombackend.excelr.model.Order;
import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService; // Assuming you have a UserService to find User by ID

    // Admin methods
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, String status) {
        try {
            Order order = orderRepository.findById(orderId).get();
            order.setStatus(status);
            return orderRepository.save(order);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Order not found", e);
        }
    }

    public void cancelOrder(Long orderId) {
        try {
            Order order = orderRepository.findById(orderId).get();
            orderRepository.delete(order);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Order not found", e);
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // User-specific methods
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order saveOrderForUser(Long userId, Order order) {
        try {
            User user = userService.findById(userId).get();
            order.setUser(user);
            return orderRepository.save(order);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("User not found", e);
        }
    }

    public Order updateOrderStatusForUser(Long userId, Long orderId, String status) {
        try {
            Order order = orderRepository.findByIdAndUserId(orderId, userId).get();
            order.setStatus(status);
            return orderRepository.save(order);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Order not found for this user", e);
        }
    }

    public void cancelOrderForUser(Long userId, Long orderId) {
        try {
            Order order = orderRepository.findByIdAndUserId(orderId, userId).get();
            orderRepository.delete(order);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Order not found for this user", e);
        }
    }
}
