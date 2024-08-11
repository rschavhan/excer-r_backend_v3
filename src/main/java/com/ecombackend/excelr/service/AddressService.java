package com.ecombackend.excelr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecombackend.excelr.model.Address;
import com.ecombackend.excelr.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAddressesByUser(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            address.setAddressLine1(updatedAddress.getAddressLine1());
            address.setAddressLine2(updatedAddress.getAddressLine2());
            address.setCity(updatedAddress.getCity());
            address.setState(updatedAddress.getState());
            address.setPostalCode(updatedAddress.getPostalCode());
            address.setCountry(updatedAddress.getCountry());
            return addressRepository.save(address);
        } else {
            return null; // Could also throw a custom exception here
        }
    }

    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
