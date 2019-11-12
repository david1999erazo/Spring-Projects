        package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.math.BigDecimal;

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
import com.example.thymeleafMIO.model.BusType;
import com.example.thymeleafMIO.model.Tmio1Bus;

@SpringBootTest(classes = thymeleafMIOApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
public class testBuses {

	@Autowired
	private BusesDao busesDao;

	public void setUpBuses() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(10));
		bus.setTipo(BusType.T);
		bus.setMarca("Chevrolet");
		bus.setPlaca("DCS243");
		bus.setModelo(new BigDecimal("2019"));
		busesDao.save(bus);
	}

	public void setUpBuses2() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(3));
		bus.setTipo(BusType.A);
		bus.setMarca("NISSAN");
		bus.setPlaca("BHD321");
		bus.setModelo(new BigDecimal("2000"));
		busesDao.save(bus);
	}

	public void setUpBuses3() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(12));
		bus.setTipo(BusType.T);
		bus.setMarca("HYUNDAY");
		bus.setPlaca("DKS324");
		bus.setModelo(new BigDecimal("2001"));
		busesDao.save(bus);
	}

	public void setUpBuses4() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(8));
		bus.setTipo(BusType.A);
		bus.setMarca("MAZDA");
		bus.setPlaca("POI987");
		bus.setModelo(new BigDecimal("2005"));
		busesDao.save(bus);
	}

	public void setUpBuses5() {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(9));
		bus.setTipo(BusType.P);
		bus.setMarca("VOLVO");
		bus.setPlaca("UYT132");
		bus.setModelo(new BigDecimal("2002"));
		busesDao.save(bus);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlaca() {
		setUpBuses();
		assertEquals("DCS243", busesDao.finByPlaca("DCS243").get(0).getPlaca());
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMarca() {
		setUpBuses2();
		assertEquals("NISSAN", busesDao.findByMarca("NISSAN").get(0).getMarca());
		assertNotNull(busesDao.findByMarca("NISSAN"));

	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModelo() {
		setUpBuses3();
		
		
		assertNotNull(busesDao.findByModelo(new BigDecimal("2001")));
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveBus() {
		
		Tmio1Bus bus = new Tmio1Bus();
		bus.setCapacidad(new BigDecimal(8));
		bus.setTipo(BusType.P);
		bus.setMarca("HONDA");
		bus.setPlaca("IFG243");
		bus.setModelo(new BigDecimal("2009"));
		
		busesDao.save(bus);
		assertNotNull(busesDao.finByPlaca("IFG243").get(0));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateBus() {
		setUpBuses4();

		Tmio1Bus bus = busesDao.finByPlaca("POI987").get(0);
		bus.setPlaca("MNB987");
		busesDao.update(bus);
		assertEquals("MNB987", busesDao.finByPlaca("MNB987").get(0).getPlaca());
	}

	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteBus() {
		setUpBuses5();
		busesDao.delete(busesDao.finByPlaca("UYT132").get(0));
		assertTrue(busesDao.finByPlaca("UYT132").isEmpty());

	}

}
