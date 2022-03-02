package com.example.beautystoreproject.service.impl;

import com.example.beautystoreproject.entities.Category;
import com.example.beautystoreproject.repository.CategoryRepository;
import com.example.beautystoreproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
