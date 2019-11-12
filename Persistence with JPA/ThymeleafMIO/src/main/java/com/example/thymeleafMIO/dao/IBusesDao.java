package com.example.thymeleafMIO.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.thymeleafMIO.model.Tmio1Bus;


public interface IBusesDao  {

	public void save(Tmio1Bus entity);
	public void update(Tmio1Bus entity);
	public void delete(Tmio1Bus entity);
	public Tmio1Bus findById(Integer codigo);
	public List<Tmio1Bus> findAll(); 
	
	//Consultas
	public List<Tmio1Bus> finByPlaca(String placa);
	public List<Tmio1Bus> findByMarca(String marca);
	public List<Tmio1Bus> findByModelo(BigDecimal modelo);
	public List<Tmio1Bus> ConsultBusesConMasDeUnServicioPorMismoDia();
	
	
}
