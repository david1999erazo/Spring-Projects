package com.example.thymeleafMIO.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.thymeleafMIO.model.UserApp;
import com.example.thymeleafMIO.model.UserGender;
import com.example.thymeleafMIO.model.UserType;
import com.example.thymeleafMIO.repositories.UserRepository;

public class UserService implements UserServiceInt {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void save(UserApp user) {
		userRepository.save(user);

	}

	public Optional<UserApp> findById(long id) {

		return userRepository.findById(id);
	}

	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}

	public Iterable<UserApp> findAllAdministrators() {
		return userRepository.findByType(UserType.admin);
	}

	public Iterable<UserApp> findAllOperatos() {
		return userRepository.findByType(UserType.operator);
	}

	public UserGender[] getGenders() {

		return UserGender.values();
	}

	public UserType[] getTypes() {
		return UserType.values();
	}

	public void delete(UserApp user) {
		userRepository.delete(user);

	}
	
}
