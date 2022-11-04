package br.com.market.addresses.repository;

import br.com.market.addresses.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Address findByUserId(Integer userId);
}
