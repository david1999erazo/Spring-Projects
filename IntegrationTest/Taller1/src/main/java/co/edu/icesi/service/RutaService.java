package co.edu.icesi.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.model.Tmio1ServiciosSitio;
import co.edu.icesi.repository.IRutaRepository;
import co.edu.icesi.repository.RutaRepository;

@Service
public class RutaService implements IRutaService {


	private IRutaRepository repository;
	
	public RutaService() {
		repository = new RutaRepository();
	}
	
	
	@Override
	public Tmio1Ruta createRuta(Tmio1Ruta ruta) throws Exception {
	
		if((ruta.getHoraFin().intValue()-ruta.getHoraInicio().intValue())<0 || (ruta.getHoraFin().intValue()-ruta.getHoraInicio().intValue())==0) {
			throw new Exception();
		}
		
		
		repository.createRutas(ruta);
		return ruta;
	}

	@Override
	public void updateRuta(Tmio1Ruta ruta) {
		repository.updateRutas(ruta);
	}

	@Override
	public void deleteRuta(String id) {
		repository.deleteRutas(id);

	}

	@Override
	public List<Tmio1Ruta> realAllRutas() {
		return repository.realAllRutas();
	}


	@Override
	public Tmio1Ruta searchRuta(String id) {
		return repository.searchRutas(id);
	}

}
