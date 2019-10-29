package co.edu.icesi.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;

public interface IConductorService {

	public Tmio1Conductore creatConductor(Tmio1Conductore conductor) throws Exception;
	public Tmio1Conductore updateConductor(Tmio1Conductore conductor) throws Exception;
	public void deleteConductor(String id);
	public Tmio1Conductore searchConductor(String id);
	public List<Tmio1Conductore> readAllConductores();

}
