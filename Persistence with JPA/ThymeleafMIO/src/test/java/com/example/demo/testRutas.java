package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

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
import com.example.thymeleafMIO.dao.RutasDao;
import com.example.thymeleafMIO.model.Tmio1Ruta;

@SpringBootTest(classes = thymeleafMIOApplication.class)
@RunWith(SpringRunner.class)
@Rollback(false)
public class testRutas {

	@Autowired
	private RutasDao rutasDao;

	public void setUpRutas() {

		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("1");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 70 until street 92");
		ruta.setDiaInicio(new BigDecimal(4));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(2000));
		rutasDao.save(ruta);

	}
	
	public void setUpRutas2() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("2");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 90 until street 32");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(2));
		ruta.setHoraFin(new BigDecimal(4000));
		rutasDao.save(ruta);
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSaveRuta() {
		
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("3");
		ruta.setActiva("yes");
		ruta.setDescripcion("Street 78 until street 12");
		ruta.setDiaInicio(new BigDecimal(2));
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setHoraInicio(new BigDecimal(3));
		ruta.setHoraFin(new BigDecimal(5000));
		
		rutasDao.save(ruta);
		assertNotNull(rutasDao.findAll());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdateRuta() {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setNumero("4");
		ruta.setActiva("no");
		ruta.setDescripcion("Street 23 until street 12");
		ruta.setDiaInicio(new BigDecimal(3));
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setHoraInicio(new BigDecimal(2));
		ruta.setHoraFin(new BigDecimal(5000));
		rutasDao.save(ruta);
		
		Tmio1Ruta rut = rutasDao.findById(ruta.getId());
		rut.setActiva("yes");
		rutasDao.update(rut);
		assertEquals("yes", rutasDao.findById(rut.getId()).getActiva());
	}
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testDeleteRuta() {
		setUpRutas2();

		Integer n = rutasDao.findAll().get(0).getId();
		rutasDao.delete(rutasDao.findById(n));
		assertNull(rutasDao.findById(n));
	}
	
	
	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByHoras() {
		setUpRutas();
		assertNotNull(rutasDao.findByRangeHours(new BigDecimal(1), new BigDecimal(4000)));
	}

	@Test
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDias() {
		setUpRutas();
		assertNotNull(rutasDao.findByRangeDates(new BigDecimal(1), new BigDecimal(7)));
	}

}
