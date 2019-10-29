package co.edu.icesi;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.repository.IConductorRepository;
import co.edu.icesi.service.ConductorService;

@SpringBootTest
public class ConductorServiceTest {

	@Mock
	private IConductorRepository mockConductor;

	@InjectMocks
	private ConductorService conductorService;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConductorContratoValido() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("123");
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("22-09-2019");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);

			when(mockConductor.createConductor(conductor)).thenReturn(conductor);
			assertEquals(conductorService.creatConductor(conductor), conductor);
			verify(mockConductor, times(1)).createConductor(conductor);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testConductorContratoInvalido1() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("123");
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("22-09-1950");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);

			conductorService.creatConductor(conductor);
		} catch (Exception e) {
			assertTrue(true);
		}
		verifyZeroInteractions(mockConductor);

	}

	@Test
	public void testConductorContratoInvalido2() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("123");
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("29-03-1999");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);

			conductorService.creatConductor(conductor);
		} catch (Exception e) {
			assertTrue(true);
		}
		verifyZeroInteractions(mockConductor);
	}

	@Test
	public void testConductorNonNull() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("123");

		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("12-01-2019");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);

			
			when(mockConductor.createConductor(conductor)).thenReturn(conductor);
			assertEquals(conductorService.creatConductor(conductor),conductor);
			assertNotNull(conductorService.creatConductor(conductor));
			verify(mockConductor, times(2)).createConductor(conductor);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void testBusNull() {
		Tmio1Conductore conductor = null;

		try {
			conductorService.creatConductor(conductor);
		} catch (Exception e) {
			assertTrue(true);
		}
		verifyZeroInteractions(mockConductor);
	}

}
