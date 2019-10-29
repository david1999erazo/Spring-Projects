package com.example.thymeleafMIO.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleafMIO.exceptions.DriverNullException;
import com.example.thymeleafMIO.exceptions.DateNullException;
import com.example.thymeleafMIO.exceptions.InconsistentDateException;
import com.example.thymeleafMIO.exceptions.WrongFormatException;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.repositories.ConductoresRepository;

@Service
public class ConductorService {

	@Autowired
	private ConductoresRepository repository;

	public Tmio1Conductore save(Tmio1Conductore conductor) throws DriverNullException,
			InconsistentDateException, DateNullException, WrongFormatException {

		if (conductor == null) {
			throw new DriverNullException();
		} else if (conductor.getFechaNacimiento() == null || conductor.getFechaContratacion() == null) {
			throw new DateNullException();
		} else if (conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == -1
				|| conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) == 0) {
			throw new InconsistentDateException();
		} else {
			return repository.save(conductor);
		}
	}

	public void setRepository(ConductoresRepository repository) {
		this.repository = repository;
	}

	public Iterable<Tmio1Conductore> findAll(){
		return repository.findAll();
	}
	
	public Optional<Tmio1Conductore> findById(String cedula) {

		return repository.findById(cedula);
	}
	
}
