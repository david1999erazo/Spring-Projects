package com.example.thymeleafMIO.services;

import java.util.Optional;

import com.example.thymeleafMIO.model.UserApp;
import com.example.thymeleafMIO.model.UserGender;
import com.example.thymeleafMIO.model.UserType;

public interface UserServiceInt {

	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdministrators();

	public Iterable<UserApp> findAllOperatos();

	public void delete(UserApp user);

	public UserGender[] getGenders();

	public UserType[] getTypes();
}
