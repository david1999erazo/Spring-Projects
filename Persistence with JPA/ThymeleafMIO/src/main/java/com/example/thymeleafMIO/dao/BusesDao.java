package com.example.thymeleafMIO.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.thymeleafMIO.model.Tmio1Bus;

@Repository
@Transactional
public class BusesDao implements IBusesDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Bus entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Tmio1Bus entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(Tmio1Bus entity) {
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));
	}

	@Override
	public Tmio1Bus findById(Integer codigo) {
		return entityManager.find(Tmio1Bus.class, codigo);
	}

	@Override
	public List<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> finByPlaca(String placa) {
		String jpql = "Select a from Tmio1Bus a where a.placa = '" + placa + "'";
		return entityManager.createQuery(jpql).getResultList();

	}

	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		String jpql = "Select a from Tmio1Bus a where a.marca = '" + marca + "'";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		String jpql = "Select a from Tmio1Bus a where a.modelo = " + modelo;
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public List<Tmio1Bus> ConsultBusesConMasDeUnServicioPorMismoDia() {
		
		String jpql = "SELECT DISTINCT buses1 FROM Tmio1Bus buses1 JOIN buses1.tmio1Servicios servicios1, "
				+ "Tmio1Bus buses2 JOIN buses2.tmio1Servicios servicios2 "
				+ "WHERE buses1.id = buses2.id "
				+ "AND ((servicios1.tmio1Ruta.diaInicio BETWEEN servicios2.tmio1Ruta.diaInicio AND servicios2.tmio1Ruta.diaFin) "
				+ "OR (servicios1.tmio1Ruta.diaFin BETWEEN servicios2.tmio1Ruta.diaInicio AND servicios2.tmio1Ruta.diaFin)) "
				+ "GROUP BY buses1 "
				+ "HAVING COUNT(servicios1) > 1";
		
		return entityManager.createQuery(jpql).getResultList();
	}
	
	
	

}
