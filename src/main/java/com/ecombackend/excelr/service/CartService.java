package com.ecombackend.excelr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecombackend.excelr.model.Cart;
import com.ecombackend.excelr.model.Product;
import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.repository.CartRepository;
import com.ecombackend.excelr.repository.ProductRepository;
import com.ecombackend.excelr.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;  // Add this to access user information

    public List<Cart> getAllCartItems() {
        return cartRepository.findAll();
    }

    public Cart saveCartItem(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }

    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setUser(user);  // Set the user for the cart item

        return cartRepository.save(cart);
    }
}
