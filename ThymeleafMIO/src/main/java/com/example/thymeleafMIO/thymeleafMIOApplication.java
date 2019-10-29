package com.example.thymeleafMIO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.thymeleafMIO.model.BusType;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.model.Tmio1Ruta;
import com.example.thymeleafMIO.model.UserApp;
import com.example.thymeleafMIO.model.UserType;
import com.example.thymeleafMIO.repositories.BusesRepository;
import com.example.thymeleafMIO.repositories.ConductoresRepository;
import com.example.thymeleafMIO.repositories.RutasRepository;
import com.example.thymeleafMIO.repositories.UserRepository;

@SpringBootApplication
public class thymeleafMIOApplication {

	public static void main(String[] args) {
		SpringApplication.run(thymeleafMIOApplication.class, args);
	}

	@Bean
	public CommandLineRunner thymeleafMIO(UserRepository userRepository, BusesRepository busRepository, ConductoresRepository conductorRepository,
			RutasRepository rutaRepository) {
		return (args) -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        
			UserApp user = new UserApp();			
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("1234"));
			user.setType(UserType.admin);
			userRepository.save(user);
			
			
			UserApp user2 = new UserApp();			
			user2.setUsername("operator");
			user2.setPassword(passwordEncoder.encode("1234"));
			user2.setType(UserType.operator);
			userRepository.save(user2);
			
			Tmio1Bus bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(10));
			bus.setTipo(BusType.T);
			bus.setMarca("Chevrolet");
			bus.setPlaca("DCS243");
			bus.setModelo(new BigDecimal("2019"));
			busRepository.save(bus);
			
			bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(12));
			bus.setTipo(BusType.A);
			bus.setMarca("Nissan");
			bus.setPlaca("FGH098");
			bus.setModelo(new BigDecimal("2008"));
			busRepository.save(bus);
			
			bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(20));
			bus.setTipo(BusType.P);
			bus.setMarca("Volvo");
			bus.setPlaca("RGK321");
			bus.setModelo(new BigDecimal("2017"));
			busRepository.save(bus);
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Tmio1Conductore conductor = new Tmio1Conductore();
			conductor.setFechaContratacion(format.parse("2015-04-12"));
			conductor.setFechaNacimiento(format.parse("1999-03-29"));
			conductor.setCedula("11511968728");
			conductor.setNombre("Juan Carlos");
			conductor.setApellidos("Cabrera Zapata");
			conductorRepository.save(conductor);
			
			conductor = new Tmio1Conductore();
			conductor.setFechaContratacion(format.parse("2010-07-13"));
			conductor.setFechaNacimiento(format.parse("1997-04-15"));
			conductor.setCedula("99388281883");
			conductor.setNombre("Milena");
			conductor.setApellidos("Gutierrez");
			conductorRepository.save(conductor);
			
			conductor = new Tmio1Conductore();
			conductor.setFechaContratacion(format.parse("2011-10-20"));
			conductor.setFechaNacimiento(format.parse("1990-01-05"));
			conductor.setCedula("44323456768");
			conductor.setNombre("Sebastian");
			conductor.setApellidos("Erazo");
			conductorRepository.save(conductor);
			
			Tmio1Ruta ruta = new Tmio1Ruta();
			ruta.setNumero("1");
			ruta.setActiva("yes");
			ruta.setDescripcion("Street 70 until street 92");
			ruta.setDiaInicio(new BigDecimal(4));
			ruta.setDiaFin(new BigDecimal(5));
			ruta.setHoraInicio(new BigDecimal(1));
			ruta.setHoraFin(new BigDecimal(2000));
			rutaRepository.save(ruta);
			
		
			
		};
	}
}
