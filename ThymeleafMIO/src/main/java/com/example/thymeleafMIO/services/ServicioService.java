package com.example.thymeleafMIO.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleafMIO.exceptions.BusNullException;
import com.example.thymeleafMIO.exceptions.DriverNullException;
import com.example.thymeleafMIO.exceptions.InconsistentDateException;
import com.example.thymeleafMIO.exceptions.RouteNullException;
import com.example.thymeleafMIO.exceptions.ServiceNullException;
import com.example.thymeleafMIO.model.Tmio1Servicio;
import com.example.thymeleafMIO.model.Tmio1ServicioPK;
import com.example.thymeleafMIO.repositories.ServiciosRepository;

@Service
public class ServicioService {

	@Autowired
	private ServiciosRepository repository;

	public Tmio1Servicio save(Tmio1Servicio servicio) throws BusNullException, DriverNullException,
			RouteNullException, InconsistentDateException, ServiceNullException {

		if (servicio == null) {
			throw new ServiceNullException();
		} else {

			Tmio1ServicioPK pk = new Tmio1ServicioPK();
			pk.setCedulaConductor(servicio.getTmio1Conductore().getCedula());
			pk.setFechaFin(servicio.getTmioFechaFin());
			pk.setFechaInicio(servicio.getTmioFechaInicio());
			pk.setIdBus(servicio.getTmio1Bus().getId());
			pk.setIdRuta(servicio.getTmio1Ruta().getId());

			servicio.setId(pk);
			servicio.setId_hash(pk.getHashCode());
		}

		if (servicio.getTmio1Bus() == null) {
			throw new BusNullException();
		} else if (servicio.getTmio1Conductore() == null) {
			throw new DriverNullException();
		} else if (servicio.getTmio1Ruta() == null) {
			throw new RouteNullException();
		} else if (servicio.getTmioFechaInicio().compareTo(servicio.getTmioFechaFin()) == 1) {
			throw new InconsistentDateException();
		} else if (servicio.getTmio1Conductore().getFechaContratacion().compareTo(servicio.getTmioFechaInicio()) == 1) {
			throw new InconsistentDateException();
		} else {
			return repository.save(servicio);
		}
	}

	public Iterable<Tmio1Servicio> findAll() {
		return repository.findAll();
	}

	public Optional<Tmio1Servicio> findById(Tmio1ServicioPK id) {
		return repository.findById(id);
	}

	public void delete(Tmio1Servicio servicio) {
		repository.delete(servicio);
	}

	public Iterable<Tmio1Servicio> filter(Date inicio, Date fin) {

		Iterable<Tmio1Servicio> lista = this.findAll();
		ArrayList<Tmio1Servicio> resultado = new ArrayList<>();
		for (Tmio1Servicio t : lista) {
			if (t.getTmioFechaInicio().compareTo(inicio) >= 0 && t.getTmioFechaFin().compareTo(fin) <= 0) {
				resultado.add(t);
			}
		}

		return resultado;
	}

}
