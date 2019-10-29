package co.edu.icesi.repository;

import java.util.List;

import co.edu.icesi.model.Tmio1Bus;

public interface IBusRepository {

	
	public Tmio1Bus createBus(Tmio1Bus newBus);
	public Tmio1Bus updateBus(Tmio1Bus newBus);
	public void deletecBus(Integer id);
	public Tmio1Bus searchBus(Integer id);
	public List<Tmio1Bus> realAllBuses();
	
	
}
