package br.com.market.demands.service;

import br.com.market.demands.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@FeignClient("products")
public interface ProductService {

    @GetMapping("/products")
    List<Product> findAllProducts();

    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);
}
