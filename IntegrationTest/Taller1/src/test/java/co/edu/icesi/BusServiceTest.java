package co.edu.icesi;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.repository.IBusRepository;
import co.edu.icesi.service.BusService;

@SpringBootTest
public class BusServiceTest {

	@Mock
	private IBusRepository mockBus;

	@InjectMocks
	private BusService busService;

	@BeforeTest
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreatBusCapacidadValida() {

		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(5));
		bus.setTipo("T");
		bus.setId(1);

		try {
			when(mockBus.createBus(bus)).thenReturn(bus);
			assertEquals(busService.creatBus(bus), bus);
			verify(mockBus, times(1)).createBus(bus);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void testBusCapacidadValidaCero() {	
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("T");
		bus.setCapacidad(new BigDecimal(0));
		
		try {
			
			when(mockBus.createBus(bus)).thenReturn(bus);
			assertEquals(busService.creatBus(bus),bus);
			verify(mockBus,times(1)).createBus(bus);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBusCapacidadInvalida() {	
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("T");
		bus.setCapacidad(new BigDecimal(-5));
		
		try {
			busService.creatBus(bus);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		verifyZeroInteractions(mockBus);
	}
	
	@Test
	public void testBusTipoTValido() {	
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("T");
		bus.setCapacidad(new BigDecimal(0));
		
		try {
			when(mockBus.createBus(bus)).thenReturn(bus);
			assertEquals(busService.creatBus(bus),bus);
			verify(mockBus,times(1)).createBus(bus);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBusTipoAValido() {	
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("A");
		bus.setCapacidad(new BigDecimal(0));
		
		try {
			when(mockBus.createBus(bus)).thenReturn(bus);
			assertEquals(busService.creatBus(bus),bus);
			verify(mockBus,times(1)).createBus(bus);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBusTipoPValido() {		
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("P");
		bus.setCapacidad(new BigDecimal(0));
		
		try {
			when(mockBus.createBus(bus)).thenReturn(bus);
			assertEquals(busService.creatBus(bus),bus);
			verify(mockBus,times(1)).createBus(bus);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBusTipoCInvalido() {	
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setTipo("C");
		bus.setCapacidad(new BigDecimal(0));
		
		try {
			busService.creatBus(bus);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		verifyZeroInteractions(mockBus);
	}
	
	@Test
	public void testBusNonNull() {	
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(1);
		bus.setTipo("P");
		bus.setCapacidad(new BigDecimal(1));
		
		try {
			when(mockBus.createBus(bus)).thenReturn(bus);
			assertEquals(busService.creatBus(bus),bus);
			assertNotNull(busService.creatBus(bus));
			verify(mockBus,times(2)).createBus(bus);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testBusNull() {	
		
		Tmio1Bus bus = null;
		
		try {
			busService.creatBus(bus);
		} catch (Exception e) {
			assertTrue(true);
		}
		verifyZeroInteractions(mockBus);
	}

	
	

}
