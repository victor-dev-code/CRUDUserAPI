package com.es.examen.crud_ec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.es.examen.crud_ec.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
