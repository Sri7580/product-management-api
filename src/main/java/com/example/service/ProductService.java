package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    // Update product details (Allows Partial Updates)
    public Product updateProduct(String id, Product productDetails) {
        Product product = getProductById(id);

        if (productDetails.getName() != null && !productDetails.getName().isEmpty()) {
            product.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null && !productDetails.getDescription().isEmpty()) {
            product.setDescription(productDetails.getDescription());
        }
        if (productDetails.getPrice() > 0) {
            product.setPrice(productDetails.getPrice());
        }
        if (productDetails.getQuantity() > 0) {
            product.setQuantity(productDetails.getQuantity());
        }
        if (productDetails.getCategory() != null && !productDetails.getCategory().isEmpty()) {
            product.setCategory(productDetails.getCategory());
        }

        return productRepository.save(product);
    }

    // Delete a product
    public void deleteProduct(String id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
