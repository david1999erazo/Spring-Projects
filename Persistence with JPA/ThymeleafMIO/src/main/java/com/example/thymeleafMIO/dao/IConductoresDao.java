package com.example.thymeleafMIO.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.thymeleafMIO.model.Tmio1Conductore;

public interface IConductoresDao {

	public void save(Tmio1Conductore entity);
	public void update(Tmio1Conductore entity);
	public void delete(Tmio1Conductore entity);
	public Tmio1Conductore findById(String codigo);
	public List<Tmio1Conductore> findAll();
	
	//Consultas
	public List<Tmio1Conductore> findByName(String name);
	public List<Tmio1Conductore> findByLastName(String apellidos);
	public List<Object[]> ConsultCantidadDeServiciosPorConductorPorFecha(LocalDate date);
	
	
	
}
