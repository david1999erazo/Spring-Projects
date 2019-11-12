package com.example.thymeleafMIO.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.example.thymeleafMIO.model.Tmio1Ruta;



public interface IRutasDao {

	public void save(Tmio1Ruta entity);
	public void update(Tmio1Ruta entity);
	public void delete(Tmio1Ruta entity);
	public Tmio1Ruta findById(Integer id);
	public List<Tmio1Ruta> findAll(); 

	//Consultas
	
	public List<Tmio1Ruta> findByRangeHours(BigDecimal horaIni, BigDecimal horaFin);
	public List<Tmio1Ruta> findByRangeDates(BigDecimal diaIni, BigDecimal diaFin);
	public List<Tmio1Ruta> ConsultrutasConServiciosConCantidadMenorADiezPorFecha(LocalDate date);
	
	
	
}
