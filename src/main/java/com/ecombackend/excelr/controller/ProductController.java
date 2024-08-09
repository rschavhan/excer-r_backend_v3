package com.ecombackend.excelr.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecombackend.excelr.dto.ProductRequest;
import com.ecombackend.excelr.model.Product;
import com.ecombackend.excelr.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority( 'ROLE_ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody ProductRequest productRequest) {
        Product product = productService.addProduct(productRequest);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
