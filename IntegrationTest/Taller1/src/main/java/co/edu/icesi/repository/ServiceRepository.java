package co.edu.icesi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Servicio;
@Repository
public class ServiceRepository implements IServiceRepository{

	
	private Map<Integer , Tmio1Servicio> servicios;	
	
	public ServiceRepository() {
		servicios = new HashMap<>();
	}
	
	@Override
	public Tmio1Servicio createService(Tmio1Servicio newService) {
		servicios.put(newService.getIdService(), newService);
		return newService;
	}

	@Override
	public void updateService(Tmio1Servicio newService) {
		servicios.put(newService.getIdService(), newService);
	}

	@Override
	public void deleteService(String idService) {
		servicios.remove(idService);
	}

	@Override
	public List<Tmio1Servicio> realAllServices() {
		return new ArrayList<Tmio1Servicio>(servicios.values());
	}

	@Override
	public Tmio1Servicio searchService(String idService) {
		return servicios.get(idService);
	}

}
