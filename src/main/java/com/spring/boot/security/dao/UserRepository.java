package com.spring.boot.security.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Repository represents the DAO layer which typically does all database operation
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String user);

	
}
