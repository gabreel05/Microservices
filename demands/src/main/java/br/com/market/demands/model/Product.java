package br.com.market.demands.model;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String productName;
    private String productPrice;
    private String productQtd;
    private String productCategory;
}
