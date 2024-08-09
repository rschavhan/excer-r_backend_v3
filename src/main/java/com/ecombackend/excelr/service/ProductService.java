package com.ecombackend.excelr.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecombackend.excelr.dto.ProductRequest;
import com.ecombackend.excelr.model.Product;
import com.ecombackend.excelr.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setPrice(productRequest.getPrice());
        return productRepository.save(product);
    }
}
