package com.example.thymeleafMIO.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.thymeleafMIO.model.Tmio1Conductore;

@Repository
@Transactional
public class ConductoresDao implements IConductoresDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public void save(Tmio1Conductore entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Conductore entity) {
		entityManager.merge(entity);	
		
	}

	@Override
	public void delete(Tmio1Conductore entity) {
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));
		
	}

	@Override
	public Tmio1Conductore findById(String cedula) {
		return entityManager.find(Tmio1Conductore.class, cedula);
	}

	@Override
	public List<Tmio1Conductore> findAll() {
		String jpql = "Select a from Tmio1Conductore a";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByName(String name) {
		String jpql = "Select a from Tmio1Conductore a where a.nombre = '"+name+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByLastName(String apellidos) {
		String jpql = "Select a from Tmio1Conductore a where a.apellidos = '"+apellidos+"'";
		return 	entityManager.createQuery(jpql).getResultList();
	
	}

	@Override
	public List<Object[]> ConsultCantidadDeServiciosPorConductorPorFecha(LocalDate date) {
		
		String jpql = "SELECT conductores,COUNT(servicios) FROM Tmio1Conductore conductores JOIN conductores.tmio1Servicios servicios "
				+ "WHERE servicios.id.fechaInicio <= :date "
				+ "AND servicios.id.fechaFin >= :date " 
				+ "GROUP BY conductores";
		return 	entityManager.createQuery(jpql).setParameter("date", date).getResultList();
	}


	

	
	
}
