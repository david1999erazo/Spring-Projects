package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.aspectj.lang.annotation.Before;
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
import com.example.thymeleafMIO.dao.RutasDao;
import com.example.thymeleafMIO.dao.ServicioDao;
import com.example.thymeleafMIO.exceptions.BusNullException;
import com.example.thymeleafMIO.exceptions.BusTypeException;
import com.example.thymeleafMIO.exceptions.CapacityNullException;
import com.example.thymeleafMIO.exceptions.SaveBusException;
import com.example.thymeleafMIO.exceptions.TypeNullException;
import com.example.thymeleafMIO.model.BusType;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.model.Tmio1Ruta;
import com.example.thymeleafMIO.model.Tmio1Servicio;
import com.example.thymeleafMIO.model.Tmio1ServicioPK;

@SpringBootTest(classes = thymeleafMIOApplication.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class testServicios {

	@Autowired
	private ServicioDao servicioDao;

	@Autowired
	private BusesDao busesDao;

	@Autowired
	private ConductoresDao conductoresDao;

	@Autowired
	private RutasDao rutasDao;

	
	public void setUpServcio() {
		
		// Crear bus
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(12));
		bus.setTipo(BusType.A);
		bus.setMarca("VOLVO");
		bus.setPlaca("ERT345");
		bus.setModelo(new BigDecimal("2004"));
		busesDao.save(bus);

		// Crear conductor
		
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("12345678975");
		conductor.setNombre("Pablo");
		conductor.setApellidos("Gomez");
		conductor.setFechaContratacion(LocalDate.of(2017, 04, 12));
		conductor.setFechaNacimiento(LocalDate.of(1967, 03, 29));
		conductoresDao.save(conductor);
		

		// Creat ruta
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("2");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 32 until street 12");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(2));
		ruta.setHoraFin(new BigDecimal(4000));
		rutasDao.save(ruta);

		Tmio1Servicio srv = new Tmio1Servicio();

		srv.setTmio1Bus(bus);
		srv.setTmio1Conductore(conductor);
		srv.setTmio1Ruta(ruta);
		srv.setTmioFechaInicio(LocalDate.of(2018, 06, 12));
		srv.setTmioFechaFin(LocalDate.of(2019, 06, 12));
		

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(srv.getTmio1Conductore().getCedula());
		srvPK.setIdBus(srv.getTmio1Bus().getId());
		srvPK.setIdRuta(srv.getTmio1Ruta().getId());
		srvPK.setFechaInicio(srv.getTmioFechaInicio());
		srvPK.setFechaFin(srv.getTmioFechaFin());

		srv.setId(srvPK);
		srv.setId_hash(srvPK.getHashCode());
		servicioDao.save(srv);
	}
	
	
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveServicio() {

		// Crear bus
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Chevrolet");
		bus.setPlaca("DCS243");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);

		// Crear conductor
		
		Tmio1Conductore conductor = new Tmio1Conductore();		
		conductor.setCedula("11511968728");
		conductor.setNombre("Juan Carlos");
		conductor.setApellidos("Cabrera Zapata");
		conductor.setFechaContratacion(LocalDate.of(2015, 04, 12));
		conductor.setFechaNacimiento(LocalDate.of(1999, 03, 29));
		
		conductoresDao.save(conductor);

		// Crear ruta
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);

		Tmio1Servicio srv = new Tmio1Servicio();

		srv.setTmio1Bus(bus);
		srv.setTmio1Conductore(conductor);
		srv.setTmio1Ruta(ruta);
		srv.setTmioFechaInicio(LocalDate.of(2016, 03, 12));
		srv.setTmioFechaFin(LocalDate.of(2019, 03, 21));

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(srv.getTmio1Conductore().getCedula());
		srvPK.setIdBus(srv.getTmio1Bus().getId());
		srvPK.setIdRuta(srv.getTmio1Ruta().getId());
		srvPK.setFechaInicio(srv.getTmioFechaInicio());
		srvPK.setFechaFin(srv.getTmioFechaFin());

		srv.setId(srvPK);
		srv.setId_hash(srvPK.getHashCode());

		servicioDao.save(srv);
		assertNotNull(servicioDao.findAll().get(0));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateServicio() throws ParseException {

		setUpServcio();

		Tmio1Servicio srv = servicioDao.findAll().get(0);
		
		srv.setTmioFechaInicio(LocalDate.of(2018, 06, 14));
		srv.setTmioFechaFin(LocalDate.of(2019, 06, 12));
		
		servicioDao.update(srv);
		assertNotNull(servicioDao.findById(srv.getId()));
		assertEquals(LocalDate.of(2018, 06, 14), servicioDao.findById(srv.getId()).getTmioFechaInicio());
		assertEquals(LocalDate.of(2019, 06, 12), servicioDao.findById(srv.getId()).getTmioFechaFin());
	
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteServicio() {

		setUpServcio();
	
		Tmio1Servicio srv = servicioDao.findAll().get(0);
		Tmio1ServicioPK svrK = srv.getId();
		
		servicioDao.delete(servicioDao.findById(svrK));
		assertNull(servicioDao.findById(svrK));
		
	}

	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindById() {

		setUpServcio();
		
		Tmio1Servicio srv = servicioDao.findAll().get(0);
		Tmio1ServicioPK srvPK = srv.getId();
		assertNotNull(servicioDao.findById(srvPK));
		
	}

}
