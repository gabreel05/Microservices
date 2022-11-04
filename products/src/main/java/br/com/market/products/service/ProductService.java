package br.com.market.products.service;

import br.com.market.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProducts(Product products);

    Optional<Product> fetchProductById(Long id);

    List<Product> fetchProductsList();

    Product updateProducts(Product products,
                            Long ProductsId);

    void deleteProductsById(Long ProductsId);
}
