package com.ecombackend.excelr.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecombackend.excelr.model.Cart;
import com.ecombackend.excelr.repository.CartRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    public Cart saveCartItem(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }
}
