package br.com.market.products.controller;

import br.com.market.products.model.Product;
import br.com.market.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product saveProducts(
            @RequestBody Product product) {
        return productService.saveProducts(product);
    }

    @GetMapping("/products")
    public List<Product> fetchProductsList() {
        return productService.fetchProductsList();
    }

    @GetMapping("/products/{id}")
    public Product fetchProductById(@PathVariable("id") Long id) {
        Optional<Product> product = productService.fetchProductById(id);

        return product.orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/products/{id}")
    public Product
    updateProduct(@RequestBody Product product,
                   @PathVariable("id") Long productsId) {
        return productService.updateProducts(
                product, productsId);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductById(@PathVariable("id")
                                     Long productId) {
        productService.deleteProductsById(
                productId);
        return "Deleted Successfully";
    }
}
