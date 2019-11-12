package com.example.thymeleafMIO.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleafMIO.exceptions.InvalidDateException;
import com.example.thymeleafMIO.dao.IRutasDao;
import com.example.thymeleafMIO.exceptions.DateNullException;
import com.example.thymeleafMIO.exceptions.InconsistentDateException;
import com.example.thymeleafMIO.exceptions.InvalidHourException;
import com.example.thymeleafMIO.exceptions.HourNullException;
import com.example.thymeleafMIO.exceptions.HorasNoConsistentesException;
import com.example.thymeleafMIO.exceptions.RouteNullException;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.model.Tmio1Ruta;


@Service
public class RutaService {

	@Autowired
	private IRutasDao repository;

	public Tmio1Ruta save(Tmio1Ruta ruta)
			throws RouteNullException, InconsistentDateException, HorasNoConsistentesException, DateNullException,
			HourNullException, InvalidDateException, InvalidHourException {

		if (ruta == null) {
			throw new RouteNullException();
		} else if (ruta.getDiaInicio() == null || ruta.getDiaFin() == null) {
			throw new DateNullException();
		} else if (ruta.getHoraInicio() == null || ruta.getHoraFin() == null) {
			throw new HourNullException();
		} else if (ruta.getDiaInicio().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaInicio().compareTo(new BigDecimal(7)) == 1
				|| ruta.getDiaFin().compareTo(new BigDecimal(1)) == -1
				|| ruta.getDiaFin().compareTo(new BigDecimal(7)) == 1) {
			throw new InvalidDateException();
		} else if (ruta.getHoraInicio().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraInicio().compareTo(new BigDecimal(86399)) == 1
				|| ruta.getHoraFin().compareTo(new BigDecimal(0)) == -1
				|| ruta.getHoraFin().compareTo(new BigDecimal(86399)) == 1) {
			throw new InvalidHourException();
		} else if (ruta.getDiaFin().compareTo(ruta.getDiaInicio()) == -1) {
			throw new InconsistentDateException();
		} else if (ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == -1
				|| ruta.getHoraFin().compareTo(ruta.getHoraInicio()) == 0) {
			throw new HorasNoConsistentesException();
		} else {
			repository.save(ruta);
			return ruta;
		}
	}

	public void setRepository(IRutasDao repository) {
		this.repository = repository;
	}
	
	public Iterable<Tmio1Ruta> findAll(){
		return repository.findAll();
	}
	
	public Tmio1Ruta findById(Integer id) {
		return repository.findById(id);
	}
	

}
