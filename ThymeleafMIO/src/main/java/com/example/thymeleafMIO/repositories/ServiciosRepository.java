package com.example.thymeleafMIO.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.thymeleafMIO.model.Tmio1Servicio;
import com.example.thymeleafMIO.model.Tmio1ServicioPK;

public interface ServiciosRepository extends CrudRepository<Tmio1Servicio, Tmio1ServicioPK> {

}
