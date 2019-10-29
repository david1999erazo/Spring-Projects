package co.edu.icesi.repository;

import java.util.List;

import co.edu.icesi.model.Tmio1Conductore;

public interface IConductorRepository {

	public Tmio1Conductore createConductor(Tmio1Conductore newConductor);
	public Tmio1Conductore updateConductor(Tmio1Conductore newConductor);
	public void deleteConductor(String id);
	public Tmio1Conductore searchConductor (String id);
	public List<Tmio1Conductore> realAllConductores();
}
