package com.example.thymeleafMIO.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thymeleafMIO.dao.IBusesDao;
import com.example.thymeleafMIO.exceptions.BusNullException;
import com.example.thymeleafMIO.exceptions.BusTypeException;
import com.example.thymeleafMIO.exceptions.CapacityNullException;
import com.example.thymeleafMIO.exceptions.SaveBusException;
import com.example.thymeleafMIO.exceptions.TypeNullException;
import com.example.thymeleafMIO.model.BusType;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.model.UserType;

@Service
public class BusService {

	@Autowired
	private IBusesDao repository;

	public Tmio1Bus save(Tmio1Bus bus)
			throws SaveBusException, BusNullException, BusTypeException, TypeNullException, CapacityNullException {

		if (bus == null) {
			throw new BusNullException();
		} else if (bus.getTipo() == null || bus.getTipo().equals("")) {
			throw new TypeNullException();
		} else if (bus.getCapacidad() == null) {
			throw new CapacityNullException();
		} else if ((bus.getCapacidad().compareTo(new BigDecimal(0)) <= 0)) {
			throw new SaveBusException();
		} else if (!(bus.getTipo().toString().equals("T") || bus.getTipo().toString().equals("P") || bus.getTipo().toString().equals("A"))) {
			throw new BusTypeException();
		} else {
			repository.save(bus);
			return bus;
		}
	}

	public Iterable<Tmio1Bus> findAll(){
		return repository.findAll();
	}
	public void setRepository(IBusesDao repository) {
		this.repository = repository;
	}
	public BusType[] getTypes() {
		return BusType.values();
	}
	public Tmio1Bus findById(Integer id) {
		return repository.findById(id);
	}
	
	

}
