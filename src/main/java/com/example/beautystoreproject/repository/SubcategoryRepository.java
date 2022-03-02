package com.example.beautystoreproject.repository;

import com.example.beautystoreproject.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubcategoryRepository extends JpaRepository<SubCategory, Long> {
}
