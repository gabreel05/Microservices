package br.com.market.products.service;

import br.com.market.products.model.Product;
import br.com.market.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productsRepository;


    @Override
    public Product saveProducts(Product products) {
        return productsRepository.save(products);
    }

    @Override
    public Optional<Product> fetchProductById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public List<Product> fetchProductsList() {
        return productsRepository.findAll();
    }

    @Override
    public Product
    updateProducts(Product products,
                   Long ProductsId) {
        Product depDB
                = productsRepository.findById(ProductsId)
                .get();

        if (Objects.nonNull(products.getProductName())
                && !"".equalsIgnoreCase(
                products.getProductName())) {
            depDB.setProductName(
                    products.getProductName());
        }

        if (Objects.nonNull(
                products.getProductCategory())
                && !"".equalsIgnoreCase(
                products.getProductCategory())) {
            depDB.setProductCategory(
                    products.getProductCategory());
        }

        if (Objects.nonNull(products.getProductQtd())
                && !"".equalsIgnoreCase(
                products.getProductQtd())) {
            depDB.setProductQtd(
                    products.getProductQtd());
        }

        if (Objects.nonNull(products.getProductPrice())
                && !"".equalsIgnoreCase(
                products.getProductPrice())) {
            depDB.setProductPrice(
                    products.getProductPrice());
        }

        return productsRepository.save(depDB);
    }

    @Override
    public void deleteProductsById(Long productsId) {
        productsRepository.deleteById(productsId);
    }
}
