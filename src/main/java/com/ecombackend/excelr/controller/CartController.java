package com.ecombackend.excelr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecombackend.excelr.model.Cart;
import com.ecombackend.excelr.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public List<Cart> getCartItemsForUser(@PathVariable Long userId) {
        return cartService.getCartItemsForUser(userId);
    }

    @PostMapping("/add")
    public Cart addProductToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addProductToCart(userId, productId, quantity);
    }


    @PostMapping
    public Cart addCartItem(@RequestBody Cart cart) {
        return cartService.saveCartItem(cart);
    }

    @PutMapping("/{id}")
    public Cart updateCartItemQuantity(@PathVariable Long id, @RequestParam int quantity) {
        return cartService.updateCartItemQuantity(id, quantity);
    }
    
    @DeleteMapping("/{id}")
    public void removeCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
    }
    
    @DeleteMapping("/user/{userId}")
    public void clearCartForUser(@PathVariable Long userId) {
        cartService.clearCartForUser(userId);
    }
}
