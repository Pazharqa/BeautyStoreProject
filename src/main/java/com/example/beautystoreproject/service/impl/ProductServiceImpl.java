package com.example.beautystoreproject.service.impl;

import com.example.beautystoreproject.entities.Product;
import com.example.beautystoreproject.repository.ProductsRepository;
import com.example.beautystoreproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productsRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productsRepository.getById(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productsRepository.save(product);
    }

    @Override
    public List<Product> findAllByCategoryId(Long id) {
        return productsRepository.findAllByCategoryId(id);
    }

    @Override
    public List<Product> findAllBySubcategoryId(Long id) {
        return productsRepository.findAllBySubcategoryId(id);
    }


}
