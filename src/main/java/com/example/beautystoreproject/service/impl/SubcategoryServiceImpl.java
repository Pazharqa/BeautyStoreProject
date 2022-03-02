package com.example.beautystoreproject.service.impl;

import com.example.beautystoreproject.entities.SubCategory;
import com.example.beautystoreproject.repository.SubcategoryRepository;
import com.example.beautystoreproject.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {
    @Autowired
    SubcategoryRepository subcategoryRepository;

    @Override
    public List<SubCategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }
}
