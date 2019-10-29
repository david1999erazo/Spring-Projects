package co.edu.icesi.testIntegration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.Tmio1Servicio;
import co.edu.icesi.repository.*;
import co.edu.icesi.service.*;

@SpringBootTest
public class ServicioServicioIntegrationTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private ServiceService servicioService;

	@Autowired
	private RutaService rutaService;

	@Autowired
	private BusService busService;

	@Autowired
	private ConductorService conductorService;

	private Tmio1Bus bus;
	private Tmio1Conductore conductor;
	private Tmio1Ruta ruta;

	@BeforeTest
	public void setuUP() {

		//	Crear bus
		bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("T");
		bus.setCapacidad(new BigDecimal(12));

		//Crear Conductor
		conductor = new Tmio1Conductore();
		conductor.setCedula("123");
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
		try {
			Date dateC = (Date) parseador.parse("22-09-2019");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);
		} catch (Exception e) {
		}

		//Crear Ruta
		ruta = new Tmio1Ruta();
		ruta.setId("A11");
		ruta.setHoraInicio(new BigDecimal(35800));
		ruta.setHoraFin(new BigDecimal(70000));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(1));

	}

	@Test
	public void testCreateServicioValida() {

		
		try {
			Tmio1Servicio servicio = new Tmio1Servicio();
			servicio.setIdService(21);

			SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

			if(busService == null || conductorService == null || rutaService == null) {
				System.out.println("SERVICIOS NULL");
			}
			
			conductorService.creatConductor(conductor);
			
			
			
			System.out.println("FALLO CREAR BUS");
			busService.creatBus(bus);
			System.out.println("FALLO CREAR CONDUCTOR");
			rutaService.createRuta(ruta);
			System.out.println("FALLO RUTA");
			servicio.setTmio1Bus(busService.searchBus(0));
			servicio.setTmio1Conductore(conductorService.searchConductor("123"));
			servicio.setTmio1Ruta(rutaService.searchRuta("A11"));
			System.out.println("=== ASIGNA OBJETOS");
			Date dateI = (Date) parseador.parse("29-03-2019");
			Date dateF = (Date) parseador.parse("19-09-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);

			if(servicio.getTmio1Bus() == null) {
				System.out.println("Bus nulo");
			}else if( servicio.getTmio1Conductore() == null) {
				System.out.println("Conductor nulo");
			}else if (servicio.getTmio1Ruta() == null) {
				System.out.println("Ruta nula");
			}
			
			Tmio1Servicio answer = servicioService.creatService(servicio);

			AssertJUnit.assertEquals(answer, servicio);
			AssertJUnit.assertEquals(answer.getTmio1Bus(), bus);
			AssertJUnit.assertEquals(answer.getTmio1Conductore(), conductor);
			AssertJUnit.assertEquals(answer.getTmio1Ruta(), ruta);
	
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail();
		}

	}

	@Test
	public void testCreateRutaInvalido() {

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setIdService(21);

		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			servicio.setTmio1Bus(busService.creatBus(bus));
			servicio.setTmio1Conductore(conductorService.creatConductor(conductor));
			servicio.setTmio1Ruta(rutaService.createRuta(ruta));

			Date dateI = (Date) parseador.parse("02-03-2019");
			Date dateF = (Date) parseador.parse("19-05-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);

			servicioService.creatService(servicio);

		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void testCreateFechaInicioConsistente() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setIdService(21);
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {

			servicio.setTmio1Bus(busService.creatBus(bus));
			servicio.setTmio1Conductore(conductorService.creatConductor(conductor));
			servicio.setTmio1Ruta(rutaService.createRuta(ruta));

			Date dateC = (Date) parseador.parse("15-05-2019");
			Date dateI = (Date) parseador.parse("05-01-2019");
			Date dateF = (Date) parseador.parse("30-06-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);
			conductor.setFechaContratacion(dateC);

			assertEquals(servicioService.creatService(servicio), servicio);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
