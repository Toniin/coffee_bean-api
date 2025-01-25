package com.coffee_bean.controllers;

//import com.coffee_bean.models.Product;
import com.coffee_bean.models.Product;
import com.coffee_bean.services.ProductServiceImp;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServiceImp productService;

    @GetMapping()
    public ResponseEntity<?> listAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody Product product) throws BadRequestException {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        String responseJson = """
                {
                    "message": "Product deleted successfully"
                }
                """;

        return ResponseEntity.ok(responseJson);
    }
}

