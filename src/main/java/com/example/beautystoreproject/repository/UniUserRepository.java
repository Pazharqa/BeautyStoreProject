package com.example.beautystoreproject.repository;

import com.example.beautystoreproject.entities.UniUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UniUserRepository extends JpaRepository<UniUser, Long> {
    UniUser findByEmail(String email);
}
