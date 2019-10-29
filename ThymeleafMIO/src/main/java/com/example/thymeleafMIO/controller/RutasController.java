package com.example.thymeleafMIO.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thymeleafMIO.exceptions.InvalidDateException;
import com.example.thymeleafMIO.exceptions.DateNullException;
import com.example.thymeleafMIO.exceptions.InconsistentDateException;
import com.example.thymeleafMIO.exceptions.InvalidHourException;
import com.example.thymeleafMIO.exceptions.HourNullException;
import com.example.thymeleafMIO.exceptions.HorasNoConsistentesException;
import com.example.thymeleafMIO.exceptions.RouteNullException;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.model.Tmio1Ruta;
import com.example.thymeleafMIO.services.RutaService;

@Controller
public class RutasController {

	private RutaService service; 
	
	@Autowired
	public RutasController(RutaService service) {
		this.service= service;
	}
	

	@RequestMapping(value = "/rutas", method = RequestMethod.GET)
	public String routes(Model model) {
		model.addAttribute("rutas", service.findAll() );
		return "rutas/index";
	}
	
	@GetMapping("/rutas/add-route")
	public String addRoutes(Model model) {		
		model.addAttribute("tmio1Ruta", new Tmio1Ruta());
		return "rutas/add-route";
	}
	
	@GetMapping("/rutas/consult-route")
	public String consultRoute(Model model) {
		model.addAttribute("tmio1Ruta", new Tmio1Ruta());
		return "rutas/consult-route";
	}
	
	@PostMapping("/rutas/add-route")
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Ruta tmio1Ruta,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "rutas/add-route";
			} else {
				try {
					service.save(tmio1Ruta);
				} catch (RouteNullException | InconsistentDateException | HorasNoConsistentesException
						| DateNullException | HourNullException | InvalidDateException | InvalidHourException e) {
					return "redirect:/error";
				}
			}	
		}
		return "redirect:/rutas";
	}


	@PostMapping("/rutas/consult-route")
	public String showConsultRoute(@ModelAttribute Tmio1Ruta tmio1Ruta, Model model) {

		Optional<Tmio1Ruta> r = service.findById(tmio1Ruta.getId());
		if (r.get() == null) {	
		}
		model.addAttribute("tmio1Ruta", r.get());
		return "rutas/showConsult-route";
	}
	
	
	
}

