package br.com.market.demands.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@FeignClient("addresses")
public interface AddressService {

    @GetMapping("/addresses/user/{id}")
    void findAddressByUserId(@PathVariable("id") Integer id);
}
