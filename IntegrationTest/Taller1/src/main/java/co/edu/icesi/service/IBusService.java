package co.edu.icesi.service;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;

public interface IBusService {

	public Tmio1Bus creatBus(Tmio1Bus bus) throws Exception;
	public Tmio1Bus updateBus(Tmio1Bus bus) throws Exception;
	public void deleteBus(Integer id);
	public Tmio1Bus searchBus (Integer id);
	public List<Tmio1Bus> realAllBuses();
	
}
