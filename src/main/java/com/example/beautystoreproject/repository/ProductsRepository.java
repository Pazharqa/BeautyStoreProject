package com.example.beautystoreproject.repository;

import com.example.beautystoreproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductsRepository extends JpaRepository<Product, Long>{
    List<Product> findAllByCategoryId(Long id);
    List<Product> findAllBySubcategoryId(Long id);
    List<Product> findAllByGenderId(Long id);
}


