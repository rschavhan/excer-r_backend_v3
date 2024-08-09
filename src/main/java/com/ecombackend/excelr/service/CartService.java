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

import org.springframework.transaction.annotation.Transactional;

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

//    public Cart addProductToCart(Long userId, Long productId, int quantity) {
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Cart cart = new Cart();
//        cart.setProduct(product);
//        cart.setQuantity(quantity);
//        cart.setUser(user);  // Set the user for the cart item
//
//        return cartRepository.save(cart);
//    }

    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        // Find the product and user
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the product is already in the user's cart
        Cart existingCartItem = cartRepository.findByUserAndProduct(user, product);

        if (existingCartItem != null) {
            // If the product already exists in the cart, update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            return cartRepository.save(existingCartItem);
        } else {
            // If the product does not exist in the cart, create a new cart item
            Cart newCartItem = new Cart();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            newCartItem.setUser(user);  // Set the user for the cart item

            return cartRepository.save(newCartItem);
        }
    }

    
    
    
	public List<Cart> getCartItemsForUser(Long userId) {
		
		
		return cartRepository.findByUserId(userId);
	}

	@Transactional
    public Cart updateCartItemQuantity(Long id, int quantity) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        cart.setQuantity(quantity);
        return cartRepository.save(cart);
    }	
}
