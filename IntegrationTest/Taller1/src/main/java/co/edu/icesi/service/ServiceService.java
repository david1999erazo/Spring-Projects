package co.edu.icesi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.repository.IServiceRepository;
import co.edu.icesi.repository.ServiceRepository;

@Service
public class ServiceService implements IServiceService{

	
	private IServiceRepository repository;
	
	public ServiceService() {
		repository = new ServiceRepository();
	}
	
	
	@Override
	public Tmio1Servicio creatService(Tmio1Servicio servicio) throws Exception{
		
		if(servicio.getTmio1Bus()==null) {
			throw new Exception("El bus es nulo");
		}else if(servicio.getTmio1Conductore()==null) {
			throw new Exception("El conductor es nulo");
		}else if(servicio.getTmio1Ruta()==null) {
			throw new Exception("La fecha es nula");
		}else if(servicio.getFechaFin()==null && servicio.getFechaIncio()==null) {
			throw new Exception("Falta alguna fecha");
		}else if(servicio.getFechaFin().compareTo(servicio.getFechaIncio())<=0) {
			throw new Exception("Las fechas del servicio son inconsistentes");
		}else if(servicio.getTmio1Conductore().getFechaContratacion().compareTo(servicio.getFechaFin())>0) {
			throw new Exception("Las fechas del servicio son inconsistentes con la fecha de contratacion del conductor");
		}
		
		repository.createService(servicio);
		
		
		return servicio;
	}

	@Override
	public void updateService(Tmio1Servicio service) {
		repository.updateService(service);
	}

	@Override
	public void deleteService(String id) {
		repository.deleteService(id);
	}

	@Override
	public List<Tmio1Servicio> realAllServices() {
		return repository.realAllServices();
	}

}
