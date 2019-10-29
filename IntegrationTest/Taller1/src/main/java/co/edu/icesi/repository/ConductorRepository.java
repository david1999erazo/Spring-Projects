package co.edu.icesi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Conductore;

@Repository
public class ConductorRepository implements IConductorRepository {

	
	private Map<String, Tmio1Conductore> conductores;
	
	public ConductorRepository() {
		conductores = new HashMap<>();
	}
	
	
	@Override
	public Tmio1Conductore createConductor(Tmio1Conductore newConductor) {
		conductores.put(newConductor.getCedula(), newConductor);
		return newConductor;
	}

	@Override
	public Tmio1Conductore updateConductor(Tmio1Conductore newConductor) {
		conductores.put(newConductor.getCedula(), newConductor);
		return newConductor;
	}

	@Override
	public void deleteConductor(String cedula) {
		conductores.remove(cedula);
		
	}

	@Override
	public List<Tmio1Conductore> realAllConductores() {
		return new ArrayList<Tmio1Conductore>(conductores.values());
	}


	@Override
	public Tmio1Conductore searchConductor(String id) {
		return conductores.get(id);
	}

}
