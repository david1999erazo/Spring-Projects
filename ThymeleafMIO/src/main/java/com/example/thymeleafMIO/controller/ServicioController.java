package com.example.thymeleafMIO.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thymeleafMIO.exceptions.BusNullException;
import com.example.thymeleafMIO.exceptions.DriverNullException;
import com.example.thymeleafMIO.exceptions.InconsistentDateException;
import com.example.thymeleafMIO.exceptions.RouteNullException;
import com.example.thymeleafMIO.exceptions.ServiceNullException;
import com.example.thymeleafMIO.model.Tmio1Servicio;
import com.example.thymeleafMIO.services.BusService;
import com.example.thymeleafMIO.services.ConductorService;
import com.example.thymeleafMIO.services.RutaService;
import com.example.thymeleafMIO.services.ServicioService;

@Controller
public class ServicioController {

	private ServicioService service;
	private BusService busService;
	private RutaService rutaService;
	private ConductorService conductorService;

	@Autowired
	public ServicioController(ServicioService service, BusService busService, RutaService rutaService,
			ConductorService conductorService) {
		this.service = service;
		this.busService = busService;
		this.conductorService = conductorService;
		this.rutaService = rutaService;

	}

	@RequestMapping(value = "/servicios", method = RequestMethod.GET)
	public String services(Model model) {
		model.addAttribute("servicios", service.findAll());
		return "servicios/index";
	}

	@GetMapping("/servicios/add-service")
	public String addServices(Model model) {
		model.addAttribute("buses", busService.findAll());
		model.addAttribute("conductores", conductorService.findAll());
		model.addAttribute("rutas", rutaService.findAll());
		model.addAttribute("tmio1Servicio", new Tmio1Servicio());
		return "servicios/add-service";
	}

	@GetMapping("/servicios/update-service/{id_hash}")
	public String showUpdateApps(@PathVariable("id_hash") Integer id_hash, Model model) {

		Iterable<Tmio1Servicio> servicios = service.findAll();
		Tmio1Servicio servicio = null;
		for (Tmio1Servicio ser : servicios) {
			if (ser.getId_hash().compareTo(id_hash) == 0) {
				service.delete(ser);
				servicio = ser;
				break;
			}
		}
		model.addAttribute("buses", busService.findAll());
		model.addAttribute("conductores", conductorService.findAll());
		model.addAttribute("rutas", rutaService.findAll());

		model.addAttribute("tmio1Servicio", servicio);
		return "servicios/update-service";
	}

	
	@GetMapping("/servicios/filter-service")
	public String consultService(Model model) {

		model.addAttribute("tmio1Servicio", new Tmio1Servicio());
		return "servicios/filter-service";
	}


	@PostMapping("/servicios/add-service")
	public String saveApps(@RequestParam(value = "action", required = true) String action,
			@Valid Tmio1Servicio tmio1Servicio, BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
		
				model.addAttribute("buses", busService.findAll());
				model.addAttribute("conductores", conductorService.findAll());
				model.addAttribute("rutas", rutaService.findAll());

				return "servicios/add-service";
			} else
				try {
					service.save(tmio1Servicio);
				} catch (BusNullException | DriverNullException | RouteNullException | InconsistentDateException
						| ServiceNullException e) {
					return "redirect:/error";
				}

			model.addAttribute("buses", busService.findAll());
			model.addAttribute("conductores", conductorService.findAll());
			model.addAttribute("rutas", rutaService.findAll());

		}
		return "redirect:/servicios";
	}


	@PostMapping("/servicios/update-service/{id_hash}")
	public String updateApp(@PathVariable("id_hash") Integer id_hash,
			@RequestParam(value = "action", required = true) String action, @Valid Tmio1Servicio tmio1Servicio,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "/servicios/update-service";
			}
		}
		try {
			service.save(tmio1Servicio);
		} catch (BusNullException | DriverNullException | RouteNullException | InconsistentDateException
				| ServiceNullException e) {
			return "redirect:/error";
		}

		model.addAttribute("buses", busService.findAll());
		model.addAttribute("conductores", conductorService.findAll());
		model.addAttribute("rutas", rutaService.findAll());

		return "redirect:/servicios";
	}


	@PostMapping("/servicios/filter-service")
	public String showConsultService(@ModelAttribute Tmio1Servicio tmio1Servicio, Model model) {

		Iterable<Tmio1Servicio> r = service.filter(tmio1Servicio.getTmioFechaInicio(), tmio1Servicio.getTmioFechaFin());
		model.addAttribute("servicios", r);

		return "servicios/index";
	}

}
