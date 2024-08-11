package com.ecombackend.excelr.controller;

import com.ecombackend.excelr.model.Address;
import com.ecombackend.excelr.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Get addresses by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getAddressesByUser(@PathVariable Long userId) {
        List<Address> addresses = addressService.getAddressesByUser(userId);
        if (addresses.isEmpty()) {
            return ResponseEntity.noContent().build(); // No addresses found for user
        }
        return ResponseEntity.ok(addresses);
    }

    // Add a new address
    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        Address savedAddress = addressService.addAddress(address);
        return ResponseEntity.ok(savedAddress);
    }

    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address updatedAddress) {
        Address updatedAddressResult = addressService.updateAddress(id, updatedAddress);
        if (updatedAddressResult != null) {
            return ResponseEntity.ok(updatedAddressResult);
        } else {
            return ResponseEntity.notFound().build(); // Address not found
        }
    }

    // Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // Successfully deleted
        } else {
            return ResponseEntity.notFound().build(); // Address not found
        }
    }
}
