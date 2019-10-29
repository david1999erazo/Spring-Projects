package co.edu.icesi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.repository.ConductorRepository;
import co.edu.icesi.repository.IConductorRepository;

@Service
public class ConductorService implements IConductorService{

	@Autowired
	private IConductorRepository repository;
	
	public ConductorService() {
		repository = new ConductorRepository();
	}
	
	
	@Override
	public Tmio1Conductore creatConductor(Tmio1Conductore conductor) throws Exception {
		
		if(conductor.getCedula() == null || 
				conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento())<=0) {
			throw new Exception();
		}
		
		return repository.createConductor(conductor);
		
	}

	
	
	@Override
	public Tmio1Conductore updateConductor(Tmio1Conductore conductor) throws Exception{
		return repository.updateConductor(conductor);
	}

	@Override
	public void deleteConductor(String id) {
		repository.deleteConductor(id);
		
	}

	@Override
	public List<Tmio1Conductore> readAllConductores() {
		return repository.realAllConductores();
	}


	@Override
	public Tmio1Conductore searchConductor(String id) {
		return repository.searchConductor(id);
	}

	
	
	
}
