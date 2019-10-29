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

import com.example.thymeleafMIO.exceptions.DriverNullException;
import com.example.thymeleafMIO.exceptions.DateNullException;
import com.example.thymeleafMIO.exceptions.InconsistentDateException;
import com.example.thymeleafMIO.exceptions.WrongFormatException;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.services.ConductorService;

@Controller
public class ConductorController {

	private ConductorService service;

	@Autowired
	public ConductorController(ConductorService service) {
		this.service = service;
	}

	@RequestMapping(value = "/conductores", method = RequestMethod.GET)
	public String drivers(Model model) {
		model.addAttribute("conductores", service.findAll());
		return "conductores/index";
	}

	@GetMapping("/conductores/add-driver")
	public String addDrivers(Model model) {

		model.addAttribute("tmio1Conductore", new Tmio1Conductore());
		return "conductores/add-driver";
	}

	
	@GetMapping("/conductores/consult-driver")
	public String consultDriver(Model model) {
		model.addAttribute("tmio1Conductore", new Tmio1Conductore());
		return "conductores/consult-driver";
	}
	
	@PostMapping("/conductores/add-driver")
	public String saveApps(@RequestParam(value = "action", required = true) String action,@Validated Tmio1Conductore tmio1Conductore,
			BindingResult bindingResult, Model model) {
		
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				return "conductores/add-driver";
			}
			else {
				try {
					service.save(tmio1Conductore);
				} catch (DriverNullException | InconsistentDateException | DateNullException
						| WrongFormatException e) {
					return "redirect:/error";
				}
			}
		}
		return "redirect:/conductores";
	}

	
	
	@PostMapping("/conductores/consult-driver")
	public String showConsultDriver(@ModelAttribute Tmio1Conductore tmio1Conductore, Model model) {
		Optional<Tmio1Conductore> c = service.findById(tmio1Conductore.getCedula());
		if (c.get() == null) {	
		}
		model.addAttribute("tmio1Conductore", c.get());
		return "conductores/showConsult-driver";
	}
	
	
}
