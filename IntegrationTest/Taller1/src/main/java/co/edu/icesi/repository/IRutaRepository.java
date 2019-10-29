package co.edu.icesi.repository;

import java.util.List;

import co.edu.icesi.model.Tmio1Ruta;

public interface IRutaRepository {

	public Tmio1Ruta createRutas(Tmio1Ruta newRuta);
	public void updateRutas(Tmio1Ruta newRuta);
	public void deleteRutas(String id);
	public Tmio1Ruta searchRutas(String id);
	public List<Tmio1Ruta> realAllRutas();

	
	
}
