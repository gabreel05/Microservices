package br.com.market.addresses.service;

import br.com.market.addresses.model.Address;
import br.com.market.addresses.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> listAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> listAddressById(Integer id) {
        return addressRepository.findById(id);
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    public Address findByUserId(Integer id) {
        return addressRepository.findByUserId(id);
    }
}
