package com.example.beautystoreproject.service.impl;

import com.example.beautystoreproject.entities.Gender;
import com.example.beautystoreproject.repository.GenderRepository;
import com.example.beautystoreproject.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {
    @Autowired
    GenderRepository genderRepository;
    @Override
    public List<Gender> getAllGenders() {
        return genderRepository.findAll();
    }
}
