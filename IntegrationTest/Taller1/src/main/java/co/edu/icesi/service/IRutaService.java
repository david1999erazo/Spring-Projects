package co.edu.icesi.service;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;

public interface IRutaService {

	
	public Tmio1Ruta createRuta(Tmio1Ruta ruta) throws Exception;
	public void updateRuta(Tmio1Ruta ruta);
	public void deleteRuta(String id);
	public Tmio1Ruta searchRuta(String id);
	public List<Tmio1Ruta> realAllRutas();
	
	
	
}
