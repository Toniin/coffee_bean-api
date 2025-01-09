package com.coffee_bean.interfaces;

import com.coffee_bean.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(final Long productId);
    List<Product> getProductsByCategory(final Long categoryId);
    Product saveProduct(Product product) throws Exception;
    void deleteProduct(final Long productId);
}
