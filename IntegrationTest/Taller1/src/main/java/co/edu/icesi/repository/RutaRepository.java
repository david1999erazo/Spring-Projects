package co.edu.icesi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Ruta;

@Repository
public class RutaRepository implements IRutaRepository{


	private Map <String,Tmio1Ruta> rutas;
	
	public RutaRepository() {
		rutas = new HashMap<>();
	}
	
	@Override
	public Tmio1Ruta createRutas(Tmio1Ruta newRuta) {
		rutas.put(newRuta.getId(), newRuta);
		return newRuta;
	}

	@Override
	public void updateRutas(Tmio1Ruta newRuta) {
		rutas.put(newRuta.getId(), newRuta);
	}

	@Override
	public void deleteRutas(String id) {
		rutas.remove(id);
		
	}

	@Override
	public List<Tmio1Ruta> realAllRutas() {
		return new ArrayList<Tmio1Ruta>(rutas.values());
	}

	@Override
	public Tmio1Ruta searchRutas(String id) {
		return rutas.get(id);
	}

}
