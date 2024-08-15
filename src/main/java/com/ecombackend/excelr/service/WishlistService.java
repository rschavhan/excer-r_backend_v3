package com.ecombackend.excelr.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecombackend.excelr.dto.WishlistDTO;
import com.ecombackend.excelr.mapper.WishlistMapper;
import com.ecombackend.excelr.model.User;
import com.ecombackend.excelr.model.Wishlist;
import com.ecombackend.excelr.repository.WishlistRepository;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final WishlistMapper wishlistMapper;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, WishlistMapper wishlistMapper) {
        this.wishlistRepository = wishlistRepository;
        this.wishlistMapper = wishlistMapper;
    }

    public List<WishlistDTO> getWishlistByUser(User user) {
        List<Wishlist> wishlist = wishlistRepository.findByUser(user);
        return wishlist.stream().map(wishlistMapper::toDTO).collect(Collectors.toList());
    }

    public void addProductToWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = wishlistMapper.toEntity(wishlistDTO);
        wishlistRepository.save(wishlist);
    }

    @Transactional
    public void removeProductFromWishlist(WishlistDTO wishlistDTO) {
        Wishlist wishlist = wishlistMapper.toEntity(wishlistDTO);
        wishlistRepository.deleteByUserAndProduct(wishlist.getUser(), wishlist.getProduct());
    }
}
