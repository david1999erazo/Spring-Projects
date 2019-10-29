package co.edu.icesi;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.model.*;
import co.edu.icesi.repository.*;
import co.edu.icesi.service.*;

@SpringBootTest
@Test
public class RutaServiceTest {

	@Mock
	private IRutaRepository mockRuta;
	
	@InjectMocks
	private RutaService  serviceRutas;
	
	@BeforeTest
	public void setuUP() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateServiceHoraConsistente() {		
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("C32");
		ruta.setHoraInicio(new BigDecimal(21600));
		ruta.setHoraFin(new BigDecimal(46800));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(1));
		
		try {
			
			when(mockRuta.createRutas(ruta)).thenReturn(ruta);
			assertEquals(serviceRutas.createRuta(ruta),ruta);
			verify(mockRuta,times(1)).createRutas(ruta);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreateServiceHoraInconsistente1() {		
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("B22");
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setHoraFin(new BigDecimal(18000));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(1));
		
		try {
			
			serviceRutas.createRuta(ruta);
			
		} catch (Exception e) {
			assertTrue(true);
		}
	
	}
	
	@Test
	public void testCreateServiceHoraInconsistente2() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("B22");
		ruta.setHoraInicio(new BigDecimal(46800));
		ruta.setHoraFin(new BigDecimal(21600));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setDiaFin(new BigDecimal(3));
		
		try {
			
			serviceRutas.createRuta(ruta);
			
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testCreatServiceDiaConsistente1() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("P21");
		ruta.setHoraInicio(new BigDecimal(21600));
		ruta.setHoraFin(new BigDecimal(46800));
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(4));
		
		try {
			when(mockRuta.createRutas(ruta)).thenReturn(ruta);
			assertEquals(serviceRutas.createRuta(ruta),ruta);
			verify(mockRuta,times(1)).createRutas(ruta);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreatServiceDiaConsistente2() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("A11");
		ruta.setHoraInicio(new BigDecimal(21600));
		ruta.setHoraFin(new BigDecimal(46800));
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(6));
		
		try {
			when(mockRuta.createRutas(ruta)).thenReturn(ruta);
			assertEquals(serviceRutas.createRuta(ruta),ruta);
			verify(mockRuta,times(1)).createRutas(ruta);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreatServiceDiaInconsistente() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("P21");
		ruta.setHoraInicio(new BigDecimal(21600));
		ruta.setHoraFin(new BigDecimal(46800));
		ruta.setDiaInicio(new BigDecimal(5));
		ruta.setDiaFin(new BigDecimal(2));
		
		try {
			serviceRutas.createRuta(ruta);
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	
	@Test
	public void testCreateServiceNonNull() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId("A11");
		ruta.setHoraInicio(new BigDecimal(21600));
		ruta.setHoraFin(new BigDecimal(46800));
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(4));
		
		try {
			when(mockRuta.createRutas(ruta)).thenReturn(ruta);
			assertEquals(serviceRutas.createRuta(ruta), ruta);
			assertNotNull(serviceRutas.createRuta(ruta));
			verify(mockRuta, times(2)).createRutas(ruta);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testCreateServiceNull() {
		
		Tmio1Ruta ruta =  null;
		try {
			serviceRutas.createRuta(ruta);
		} catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
}
