package br.com.market.addresses.controller;

import br.com.market.addresses.model.Address;
import br.com.market.addresses.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> listAddresses() {
        List<Address> addresses = addressService.listAddresses();

        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> listAddressById(@PathVariable Integer id) {
        Optional<Address> enderecoOptional = addressService.listAddressById(id);

        if (enderecoOptional.isPresent()) {
            return ResponseEntity.ok(enderecoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        Address addressSalvo = addressService.saveAddress(address);

        return ResponseEntity.status(HttpStatus.CREATED).body(addressSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Integer id) {

        addressService.deleteAddress(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Address> findByUserId(@PathVariable("id") Integer id) {
        Address address = addressService.findByUserId(id);

        return ResponseEntity.ok(address);
    }
}
