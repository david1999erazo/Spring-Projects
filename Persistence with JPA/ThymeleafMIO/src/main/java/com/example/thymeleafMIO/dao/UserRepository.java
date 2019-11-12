package com.example.thymeleafMIO.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.thymeleafMIO.model.UserApp;
import com.example.thymeleafMIO.model.UserType;

public interface UserRepository extends CrudRepository<UserApp, Long> {

	List<UserApp> findByName(String name);
	
	List<UserApp> findByUsername(String username);
	
	List<UserApp> findByType(UserType type);

}
