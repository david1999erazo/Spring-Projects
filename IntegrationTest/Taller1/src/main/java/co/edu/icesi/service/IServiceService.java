package co.edu.icesi.service;

import java.util.List;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;

public interface IServiceService {

	public Tmio1Servicio creatService(Tmio1Servicio service) throws Exception;

	public void updateService(Tmio1Servicio service);

	public void deleteService(String id);

	public List<Tmio1Servicio> realAllServices();

}
