package com.ecombackend.excelr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecombackend.excelr.model.Cart;
import com.ecombackend.excelr.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<Cart> getAllCartItems() {
        return cartService.getAllCartItems();
    }

    @PostMapping("/add")
    public Cart addProductToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addProductToCart(userId, productId, quantity);
    }


    @PostMapping
    public Cart addCartItem(@RequestBody Cart cart) {
        return cartService.saveCartItem(cart);
    }

    @DeleteMapping("/{id}")
    public void removeCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
    }
}
