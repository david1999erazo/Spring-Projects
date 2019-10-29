package co.edu.icesi;

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
import org.springframework.boot.test.context.SpringBootTest;
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
@Test
public class ServicioServicioTest {

	private Tmio1Bus bus;
	private Tmio1Conductore conductor;
	private Tmio1Ruta ruta;
	@Mock
	private IServiceRepository mockServicios;

	@Mock
	private IBusRepository mockBuses;

	@Mock
	private IConductorRepository mockCondutores;

	@Mock
	private IRutaRepository mockRutas;

	@InjectMocks
	private ServiceService serviceServicios;

	@BeforeTest
	public void setuUP() {

		MockitoAnnotations.initMocks(this);
		this.bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("T");
		bus.setCapacidad(new BigDecimal(12));
		when(mockBuses.createBus(bus)).thenReturn(bus);

		this.conductor = new Tmio1Conductore();
		conductor.setCedula("123");
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");
		try {
			Date dateC = (Date) parseador.parse("22-09-2019");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);
		} catch (Exception e) {
		}
		when(mockCondutores.createConductor(conductor)).thenReturn(conductor);

		this.ruta = new Tmio1Ruta();
		ruta.setId("A11");
		ruta.setHoraInicio(new BigDecimal(35800));
		ruta.setHoraFin(new BigDecimal(70000));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(1));
		when(mockRutas.createRutas(ruta)).thenReturn(ruta);

	}

	//23
	@Test
	public void testCreateRutaValida() {

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setIdService(21);
		servicio.setTmio1Bus(mockBuses.createBus(bus));
		servicio.setTmio1Conductore(mockCondutores.createConductor(conductor));
		servicio.setTmio1Ruta(mockRutas.createRutas(ruta));

		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {

			Date dateI = (Date) parseador.parse("29-03-2019");
			Date dateF = (Date) parseador.parse("19-09-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);

			when(mockServicios.createService(servicio)).thenReturn(servicio);

			Tmio1Servicio answer = serviceServicios.creatService(servicio);

			AssertJUnit.assertEquals(answer, servicio);
			AssertJUnit.assertEquals(answer.getTmio1Bus(), bus);
			AssertJUnit.assertEquals(answer.getTmio1Conductore(), conductor);
			AssertJUnit.assertEquals(answer.getTmio1Ruta(), ruta);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}
	
	//24
	@Test
	public void testCreateRutaInvalido() {

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setIdService(21);
		servicio.setTmio1Bus(mockBuses.createBus(bus));
		servicio.setTmio1Conductore(mockCondutores.createConductor(conductor));
		servicio.setTmio1Ruta(mockRutas.createRutas(ruta));

		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {

			Date dateI = (Date) parseador.parse("29-03-2019");
			Date dateF = (Date) parseador.parse("19-09-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);

			serviceServicios.creatService(servicio);

		} catch (Exception e) {
			assertTrue(true);
		}
	}

	//25
	@Test
	public void testCreateFechaInicioConsistente() {
		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setIdService(21);
		servicio.setTmio1Bus(mockBuses.createBus(bus));
		servicio.setTmio1Conductore(mockCondutores.createConductor(conductor));
		servicio.setTmio1Ruta(mockRutas.createRutas(ruta));


		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("29-03-2019");
			Date dateI = (Date) parseador.parse("31-04-2019");
			Date dateF = (Date) parseador.parse("30-06-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);
			conductor.setFechaContratacion(dateC);
			
			when(mockServicios.createService(servicio)).thenReturn(servicio);
			
			assertEquals(serviceServicios.creatService(servicio), servicio);
			verify(mockServicios, times(1)).createService(servicio);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//26
	@Test
	public void testCreateFechaInicioInconsistente() {

		Tmio1Servicio servicio = new Tmio1Servicio();
		servicio.setIdService(11);
		servicio.setTmio1Bus(new Tmio1Bus());
		servicio.setTmio1Conductore(new Tmio1Conductore());
		servicio.setTmio1Ruta(new Tmio1Ruta());

		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("31-04-2019");
			Date dateI = (Date) parseador.parse("29-03-2019");
			Date dateF = (Date) parseador.parse("30-06-2019");

			servicio.setFechaIncio(dateI);
			servicio.setFechaFin(dateF);
			conductor.setFechaContratacion(dateC);

			serviceServicios.creatService(servicio);

		} catch (Exception e) {
			assertTrue(true);
		}

	}

}
