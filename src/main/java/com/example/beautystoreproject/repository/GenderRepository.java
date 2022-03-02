package com.example.beautystoreproject.repository;

import com.example.beautystoreproject.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GenderRepository extends JpaRepository <Gender, Long> {
}
