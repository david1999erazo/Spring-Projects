package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
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
import com.example.thymeleafMIO.dao.RutasDao;
import com.example.thymeleafMIO.dao.ServicioDao;
import com.example.thymeleafMIO.model.BusType;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.model.Tmio1Ruta;
import com.example.thymeleafMIO.model.Tmio1Servicio;
import com.example.thymeleafMIO.model.Tmio1ServicioPK;

@SpringBootTest(classes = thymeleafMIOApplication.class)
@RunWith(SpringRunner.class)
@Rollback(true)
public class testComplexConsults {

	@Autowired
	private ServicioDao servicioDao;

	@Autowired
	private BusesDao busesDao;

	@Autowired
	private ConductoresDao conductoresDao;

	@Autowired
	private RutasDao rutasDao;

	public void setUpServicio() {

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
		srv.setTmioFechaInicio(LocalDate.of(2014, 06, 12));
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

		// SERVICIO 2

		// Crear bus
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(12));
		bus2.setTipo(BusType.A);
		bus2.setMarca("CHEVROLET");
		bus2.setPlaca("AAA444");
		bus2.setModelo(new BigDecimal("2003"));
		busesDao.save(bus2);

		// Crear conductor

		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2.setCedula("09876573929");
		conductor2.setNombre("CARLOS");
		conductor2.setApellidos("PATIÑO");
		conductor2.setFechaContratacion(LocalDate.of(2011, 04, 12));
		conductor2.setFechaNacimiento(LocalDate.of(1999, 03, 29));
		conductoresDao.save(conductor2);

		// Creat ruta
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("3");
		ruta2.setActiva("yes");
		ruta2.setDescripcion("Street 40 until street 21");
		ruta2.setDiaInicio(new BigDecimal(3));
		ruta2.setDiaFin(new BigDecimal(5));
		ruta2.setHoraInicio(new BigDecimal(2));
		ruta2.setHoraFin(new BigDecimal(4000));
		rutasDao.save(ruta2);

		Tmio1Servicio srv2 = new Tmio1Servicio();

		srv2.setTmio1Bus(bus2);
		srv2.setTmio1Conductore(conductor2);
		srv2.setTmio1Ruta(ruta2);
		srv2.setTmioFechaInicio(LocalDate.of(2013, 06, 12));
		srv2.setTmioFechaFin(LocalDate.of(2016, 06, 12));

		Tmio1ServicioPK srvPK2 = new Tmio1ServicioPK();
		srvPK2.setCedulaConductor(srv2.getTmio1Conductore().getCedula());
		srvPK2.setIdBus(srv2.getTmio1Bus().getId());
		srvPK2.setIdRuta(srv2.getTmio1Ruta().getId());
		srvPK2.setFechaInicio(srv2.getTmioFechaInicio());
		srvPK2.setFechaFin(srv2.getTmioFechaFin());

		srv2.setId(srvPK2);
		srv2.setId_hash(srvPK2.getHashCode());
		servicioDao.save(srv2);

	}

	public void setUpServicio2() {

		// RUTA 1 CON 7 SERVICIOS
		
		// Crear bus
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(12));
		bus2.setTipo(BusType.A);
		bus2.setMarca("MERCEDEZ");
		bus2.setPlaca("GGG444");
		bus2.setModelo(new BigDecimal("2014"));
		busesDao.save(bus2);

		// Crear conductor
		Tmio1Conductore conductor2 = new Tmio1Conductore();
		conductor2.setCedula("2371823717");
		conductor2.setNombre("Noriel");
		conductor2.setApellidos("Ortiz");
		conductor2.setFechaContratacion(LocalDate.of(2013, 04, 12));
		conductor2.setFechaNacimiento(LocalDate.of(1989, 03, 19));
		conductoresDao.save(conductor2);

		
		
		// Creat ruta
		Tmio1Ruta ruta2 = new Tmio1Ruta();
		ruta2.setNumero("2");
		ruta2.setActiva("yes");
		ruta2.setDescripcion("Street 22 until street 55");
		ruta2.setDiaInicio(new BigDecimal(2));
		ruta2.setDiaFin(new BigDecimal(4));
		ruta2.setHoraInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(3000));
		rutasDao.save(ruta2);

		for (int i = 0; i < 8; i++) {

			Tmio1Servicio srv2 = new Tmio1Servicio();

			srv2.setTmio1Bus(bus2);
			srv2.setTmio1Conductore(conductor2);
			srv2.setTmio1Ruta(ruta2);

			int dia = 1 + i;
			srv2.setTmioFechaInicio(LocalDate.of(2011, 05, dia));
			srv2.setTmioFechaFin(LocalDate.of(2019, 05, dia));

			Tmio1ServicioPK srvPK2 = new Tmio1ServicioPK();
			srvPK2.setCedulaConductor(srv2.getTmio1Conductore().getCedula());
			srvPK2.setIdBus(srv2.getTmio1Bus().getId());
			srvPK2.setIdRuta(srv2.getTmio1Ruta().getId());
			srvPK2.setFechaInicio(srv2.getTmioFechaInicio());
			srvPK2.setFechaFin(srv2.getTmioFechaFin());

			srv2.setId(srvPK2);
			srv2.setId_hash(srvPK2.getHashCode());

			servicioDao.save(srv2);
		}
		

	}

	public void setUpServicio3() {

		//BUS 1 CON 2 SERVICIOS
		
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
		conductor.setApellidos("Erazo");
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

		// Servicio1
		Tmio1Servicio srv = new Tmio1Servicio();
		srv.setTmio1Bus(bus);
		srv.setTmio1Conductore(conductor);
		srv.setTmio1Ruta(ruta);
		srv.setTmioFechaInicio(LocalDate.of(2014, 06, 03));
		srv.setTmioFechaFin(LocalDate.of(2014, 06, 05));

		Tmio1ServicioPK srvPK = new Tmio1ServicioPK();
		srvPK.setCedulaConductor(srv.getTmio1Conductore().getCedula());
		srvPK.setIdBus(srv.getTmio1Bus().getId());
		srvPK.setIdRuta(srv.getTmio1Ruta().getId());
		srvPK.setFechaInicio(srv.getTmioFechaInicio());
		srvPK.setFechaFin(srv.getTmioFechaFin());

		srv.setId(srvPK);
		srv.setId_hash(srvPK.getHashCode());
		servicioDao.save(srv);

		// Servicio2
		Tmio1Servicio srv2 = new Tmio1Servicio();
		srv2.setTmio1Bus(bus);
		srv2.setTmio1Conductore(conductor);
		srv2.setTmio1Ruta(ruta);
		srv2.setTmioFechaInicio(LocalDate.of(2014, 06, 01));
		srv2.setTmioFechaFin(LocalDate.of(2014, 06, 06));

		Tmio1ServicioPK srvPK2 = new Tmio1ServicioPK();
		srvPK2.setCedulaConductor(srv2.getTmio1Conductore().getCedula());
		srvPK2.setIdBus(srv2.getTmio1Bus().getId());
		srvPK2.setIdRuta(srv2.getTmio1Ruta().getId());
		srvPK2.setFechaInicio(srv2.getTmioFechaInicio());
		srvPK2.setFechaFin(srv2.getTmioFechaFin());

		srv2.setId(srvPK2);
		srv2.setId_hash(srvPK2.getHashCode());
		servicioDao.save(srv2);
	
		//BUS 2 CON DOS SERVICIOS

		// Crear bus
		Tmio1Bus bus2 = new Tmio1Bus();
		bus2.setCapacidad(new BigDecimal(10));
		bus2.setTipo(BusType.T);
		bus2.setMarca("YUKO");
		bus2.setPlaca("DDD222");
		bus2.setModelo(new BigDecimal("2019"));
		busesDao.save(bus2);
				
		
		// Servicio1
		Tmio1Servicio srv3 = new Tmio1Servicio();
		srv3.setTmio1Bus(bus2);
		srv3.setTmio1Conductore(conductor);
		srv3.setTmio1Ruta(ruta);
		srv3.setTmioFechaInicio(LocalDate.of(2014, 05, 29));
		srv3.setTmioFechaFin(LocalDate.of(2014, 06, 07));

		Tmio1ServicioPK srvPK3 = new Tmio1ServicioPK();
		srvPK3.setCedulaConductor(srv3.getTmio1Conductore().getCedula());
		srvPK3.setIdBus(srv3.getTmio1Bus().getId());
		srvPK3.setIdRuta(srv3.getTmio1Ruta().getId());
		srvPK3.setFechaInicio(srv3.getTmioFechaInicio());
		srvPK3.setFechaFin(srv3.getTmioFechaFin());

		srv3.setId(srvPK3);
		srv3.setId_hash(srvPK3.getHashCode());
		servicioDao.save(srv3);

		// Servicio2
		Tmio1Servicio srv4 = new Tmio1Servicio();
		srv4.setTmio1Bus(bus2);
		srv4.setTmio1Conductore(conductor);
		srv4.setTmio1Ruta(ruta);
		srv4.setTmioFechaInicio(LocalDate.of(2014, 05, 28));
		srv4.setTmioFechaFin(LocalDate.of(2014, 06, 8));

		Tmio1ServicioPK srvPK4 = new Tmio1ServicioPK();
		srvPK4.setCedulaConductor(srv4.getTmio1Conductore().getCedula());
		srvPK4.setIdBus(srv4.getTmio1Bus().getId());
		srvPK4.setIdRuta(srv4.getTmio1Ruta().getId());
		srvPK4.setFechaInicio(srv4.getTmioFechaInicio());
		srvPK4.setFechaFin(srv4.getTmioFechaFin());

		srv4.setId(srvPK4);
		srv4.setId_hash(srvPK4.getHashCode());
		servicioDao.save(srv4);

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testConsultCantidadServiciosPorFecha() {
	
		setUpServicio();

		String n = "1";
		assertEquals(conductoresDao.ConsultCantidadDeServiciosPorConductorPorFecha(LocalDate.of(2015, 05, 12)).size(),2);
		assertEquals(conductoresDao.ConsultCantidadDeServiciosPorConductorPorFecha(LocalDate.of(2015, 05, 12)).get(0)[1],Long.parseLong(n));
		assertEquals(conductoresDao.ConsultCantidadDeServiciosPorConductorPorFecha(LocalDate.of(2015, 05, 12)).get(1)[1],Long.parseLong(n));
		
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testConsultrutasConServiciosConCantidadMenorADiezPorFecha() {
		
		setUpServicio2();
		
		assertNotNull(rutasDao.ConsultrutasConServiciosConCantidadMenorADiezPorFecha(LocalDate.of(2014, 06, 04)));
		assertEquals(rutasDao.ConsultrutasConServiciosConCantidadMenorADiezPorFecha(LocalDate.of(2014, 06, 04)).size(), 1);
		assertEquals("Street 22 until street 55", rutasDao.ConsultrutasConServiciosConCantidadMenorADiezPorFecha(LocalDate.of(2014, 06, 04)).get(0).getDescripcion());
	
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testConsultBusesConMasDeUnServicioPorMismoDia() {
		
		setUpServicio3();		
		assertEquals(busesDao.ConsultBusesConMasDeUnServicioPorMismoDia().size(), 2);
		assertNotNull(busesDao.ConsultBusesConMasDeUnServicioPorMismoDia());
		assertEquals("ERT345", busesDao.ConsultBusesConMasDeUnServicioPorMismoDia().get(0).getPlaca());
		assertEquals("DDD222", busesDao.ConsultBusesConMasDeUnServicioPorMismoDia().get(1).getPlaca());
		
		
		
	}

}
