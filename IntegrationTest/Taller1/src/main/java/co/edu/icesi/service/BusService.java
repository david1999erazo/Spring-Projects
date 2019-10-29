package co.edu.icesi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.repository.BusRepository;
import co.edu.icesi.repository.IBusRepository;

@Service
public class BusService implements IBusService {

	@Autowired
	private IBusRepository repository;

	@Override
	public Tmio1Bus creatBus(Tmio1Bus bus) throws Exception {
	
		if(bus == null) {
			throw new Exception("el bus esta nulo");
		}
		else if (bus.getId() == null || bus.getTipo() == null) {
			throw new Exception("Faltan datos");
		}
		else if(!bus.getTipo().equals("T") && !bus.getTipo().equals("P") && !bus.getTipo().equals("A")) {
			throw new Exception("El tipo es erroneo");
		}else if(bus.getCapacidad().signum() < 0) {
			throw new Exception("Capacidad menor a cero");
		}
		return repository.createBus(bus);
	}

	@Override
	public Tmio1Bus updateBus(Tmio1Bus bus) throws Exception {
		return repository.updateBus(bus);
	}

	@Override
	public void deleteBus(Integer id) {
		repository.deletecBus(id);

	}

	@Override
	public List<Tmio1Bus> realAllBuses() {
		return repository.realAllBuses();
	}

	@Override
	public Tmio1Bus searchBus(Integer id) {
		return repository.searchBus(id);
	}

}
