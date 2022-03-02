package com.example.beautystoreproject.repository;

import com.example.beautystoreproject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.xml.catalog.Catalog;
@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
