package com.example.thymeleafMIO.dao;

import java.util.List;


import com.example.thymeleafMIO.model.Tmio1Servicio;
import com.example.thymeleafMIO.model.Tmio1ServicioPK;

public interface IServiciosDao {

	public void save(Tmio1Servicio entity);
	public void update(Tmio1Servicio entity);
	public void delete(Tmio1Servicio entity);
	public Tmio1Servicio findById(Tmio1ServicioPK id);
	public List<Tmio1Servicio> findAll(); 

	
}
