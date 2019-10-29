package co.edu.icesi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.edu.icesi.model.Tmio1Bus;

@Repository
public class BusRepository implements IBusRepository {

	
	private Map<Integer, Tmio1Bus> buses;
	
	public BusRepository() {
		buses = new HashMap<>();
	}
	

	@Override
	public Tmio1Bus createBus(Tmio1Bus newBus) {
		buses.put(newBus.getId(), newBus);
		return newBus;
	}

	@Override
	public Tmio1Bus updateBus(Tmio1Bus newBus) {
		buses.put(newBus.getId(), newBus);
		return newBus;
	}

	@Override
	public void deletecBus(Integer id) {
		buses.remove(id);
		
	}

	@Override
	public List<Tmio1Bus> realAllBuses() {
		
		return new ArrayList<Tmio1Bus>(buses.values());
	}


	@Override
	public Tmio1Bus searchBus(Integer id) {
		return buses.get(id);
	}
	
	
}
