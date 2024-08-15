package com.ecombackend.excelr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecombackend.excelr.dto.WishlistDTO;
import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

	@Autowired
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistDTO>> getWishlistByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<WishlistDTO> wishlist = wishlistService.getWishlistByUser(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProductToWishlist(@RequestBody WishlistDTO wishlistDTO) {
        wishlistService.addProductToWishlist(wishlistDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeProductFromWishlist(@RequestBody WishlistDTO wishlistDTO) {
        wishlistService.removeProductFromWishlist(wishlistDTO);
        return ResponseEntity.ok().build();
    }
}
