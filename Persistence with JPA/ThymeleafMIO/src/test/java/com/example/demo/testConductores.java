package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.testng.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.thymeleafMIO.thymeleafMIOApplication;
import com.example.thymeleafMIO.dao.BusesDao;
import com.example.thymeleafMIO.dao.ConductoresDao;
import com.example.thymeleafMIO.exceptions.DateNullException;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.services.ConductorService;

@SpringBootTest(classes = thymeleafMIOApplication.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class testConductores {

	@Autowired
	private ConductoresDao conductoresDao;

	public void setUpConductores() {

		
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2015, 04, 12));
		conductor.setFechaNacimiento(LocalDate.of(1999, 03, 29));
		conductor.setCedula("1151196878");
		conductor.setNombre("Juan Carlos");
		conductor.setApellidos("Cabrera Zapata");
		conductoresDao.save(conductor);
	}

	public void setUpConductores2() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2011, 10, 20));
		conductor.setFechaNacimiento(LocalDate.of(1999, 01, 05));
		conductor.setCedula("44323456768");
		conductor.setNombre("Sebastian");
		conductor.setApellidos("Erazo");
		conductoresDao.save(conductor);

	}
	
	public void setUpConductores3() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor = new Tmio1Conductore();
		conductor.setFechaContratacion(LocalDate.of(2012, 10, 15));
		conductor.setFechaNacimiento(LocalDate.of(1990, 03, 20));
		conductor.setCedula("7892834792");
		conductor.setNombre("Sara");
		conductor.setApellidos("Ortiz");
		conductoresDao.save(conductor);

	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveConductor() throws ParseException {
		
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor = new Tmio1Conductore();
		
		conductor.setCedula("1234322345");
		conductor.setNombre("Juan");
		conductor.setApellidos("Pis");		
		conductor.setFechaContratacion(LocalDate.of(2011, 10, 20));
		conductor.setFechaNacimiento(LocalDate.of(1999, 01, 05));
		
		conductoresDao.save(conductor);
		assertNotNull(conductoresDao.findById("1234322345"));
	
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testNotSaveConductor() {
		
		setUpConductores();
		
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor = new Tmio1Conductore();
		
		conductor.setCedula("1151196878"); 
		conductor.setNombre("Alejandro");
		conductor.setApellidos("Gonzalo");		
		conductor.setFechaContratacion(LocalDate.of(2019, 11, 03));
		conductor.setFechaNacimiento(LocalDate.of(1999, 03, 29));
		
		try {
			conductoresDao.save(conductor);	
		} catch (Exception e) {
			assertTrue(true);
		}
	
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateConductor() {
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor = new Tmio1Conductore();
		
		conductor.setCedula("32145672");
		conductor.setNombre("Pepe");
		conductor.setApellidos("Gonzales");
		conductor.setFechaContratacion(LocalDate.of(2011, 10, 20));
		conductor.setFechaNacimiento(LocalDate.of(1999, 01, 05));

		conductoresDao.save(conductor);
		
		Tmio1Conductore cond = conductoresDao.findByName("Pepe").get(0);
		cond.setNombre("Carlos");
		conductoresDao.update(cond);
		assertEquals("Carlos", conductoresDao.findByName("Carlos").get(0).getNombre());
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteConductor() {
		
		setUpConductores3();
		conductoresDao.delete(conductoresDao.findByName("Sara").get(0));
		assertTrue(conductoresDao.findByName("Sara").isEmpty());
		
	}
	

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNombre() {

		setUpConductores();		
		assertEquals("Juan Carlos", conductoresDao.findByName("Juan Carlos").get(0).getNombre());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByApellidos() {

		
		setUpConductores2();	
		assertEquals("Erazo", conductoresDao.findByLastName("Erazo").get(0).getApellidos());

	}

}
