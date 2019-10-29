package co.edu.icesi.testIntegration;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.repository.IConductorRepository;
import co.edu.icesi.service.ConductorService;

@SpringBootTest
public class ConductorServiceIntegrationTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private ConductorService conductorService;

	@BeforeTest
	public void initMocks() {

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

			
			assertEquals(conductorService.creatConductor(conductor), conductor);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Test
	public void testConductorNonNull() {

		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("123");
		

		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy");

		try {
			Date dateC = (Date) parseador.parse("23-03-2019");
			Date dateN = (Date) parseador.parse("29-03-1999");
			conductor.setFechaContratacion(dateC);
			conductor.setFechaNacimiento(dateN);

			
			assertEquals(conductorService.creatConductor(conductor), conductor);
			assertNotNull(conductorService.creatConductor(conductor));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

}
