package co.edu.icesi.repository;

import java.util.List;

import co.edu.icesi.model.Tmio1Servicio;

public interface IServiceRepository {

	public Tmio1Servicio createService(Tmio1Servicio newService);
	public void updateService(Tmio1Servicio newService);
	public void deleteService(String idService);
	public Tmio1Servicio searchService(String idService);
	public List<Tmio1Servicio> realAllServices();
	
	
}
