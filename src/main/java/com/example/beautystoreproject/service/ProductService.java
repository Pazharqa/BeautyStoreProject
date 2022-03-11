package com.example.beautystoreproject.service;

import com.example.beautystoreproject.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void addProduct(Product product);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    void updateProduct(Product product);
    List<Product> findAllByCategoryId(Long id);
    List<Product> findAllBySubcategoryId(Long id);
    List<Product> findAllByGenderId(Long id);
}
