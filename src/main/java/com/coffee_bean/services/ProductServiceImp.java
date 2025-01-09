package com.coffee_bean.services;

import com.coffee_bean.exceptions.CategoryNotFoundException;
import com.coffee_bean.exceptions.ProductNotFoundException;
import com.coffee_bean.interfaces.ProductService;
import com.coffee_bean.models.Product;
import com.coffee_bean.repositories.ProductRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new ProductNotFoundException("Product not found!");
        }
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        List<Product> listProductsByCategory = productRepository.findByCategory(categoryId);
        if (listProductsByCategory.isEmpty()) {
            throw new CategoryNotFoundException("Category not found!");
        }
        return listProductsByCategory;
    }

    public Product saveProduct(Product product) throws BadRequestException {
        try {
            return productRepository.save(product);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product to delete is not found!"));
        productRepository.deleteById(productId);
    }
}
