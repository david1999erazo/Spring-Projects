package com.example.thymeleafMIO.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Ruta;

@Repository
@Transactional
public class RutasDao implements IRutasDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Ruta entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Ruta entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Ruta entity) {
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}

	@Override
	public Tmio1Ruta findById(Integer id) {
		return entityManager.find(Tmio1Ruta.class, id);
	}

	@Override
	public List<Tmio1Ruta> findAll() {
		String jpql = "Select a from Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByRangeHours(BigDecimal horaIni, BigDecimal horaFin) {
		String jpql = "Select a from Tmio1Ruta a where a.horaInicio between " + horaIni + " and " + horaFin
				+ " and a.horaFin between " + horaIni + " and " + horaFin;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByRangeDates(BigDecimal diaIni, BigDecimal diaFin) {
		String jpql = "Select a from Tmio1Ruta a where a.diaInicio between " + diaIni + " and " + diaFin
				+ " and a.diaInicio between " + diaIni + " and " + diaFin;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Ruta> ConsultrutasConServiciosConCantidadMenorADiezPorFecha(LocalDate date) {
		
		String jpql = "SELECT rutas FROM Tmio1Ruta rutas JOIN rutas.tmio1Servicios servicios "
				+ "WHERE servicios.id.fechaInicio <= :date " 
				+ "AND servicios.id.fechaFin >= :date " 
				+ "GROUP BY rutas "
				+ "HAVING COUNT(servicios) < 10 ";
		
		return entityManager.createQuery(jpql).setParameter("date", date).getResultList();

	}

}
