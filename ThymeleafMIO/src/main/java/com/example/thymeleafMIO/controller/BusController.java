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

import com.example.thymeleafMIO.exceptions.BusNullException;
import com.example.thymeleafMIO.exceptions.BusTypeException;
import com.example.thymeleafMIO.exceptions.CapacityNullException;
import com.example.thymeleafMIO.exceptions.SaveBusException;
import com.example.thymeleafMIO.exceptions.TypeNullException;
import com.example.thymeleafMIO.model.Tmio1Bus;
import com.example.thymeleafMIO.model.Tmio1Conductore;
import com.example.thymeleafMIO.services.BusService;
import com.example.thymeleafMIO.services.UserServiceInt;
import com.example.thymeleafMIO.validation.Step1;

@Controller
public class BusController {

	private BusService service;

	@Autowired
	public BusController(BusService service) {
		this.service = service;
	}

	@RequestMapping(value = "/buses", method = RequestMethod.GET)
	public String buses(Model model) {
		model.addAttribute("buses", service.findAll());
		return "buses/index";
	}

	@GetMapping("/buses/add-bus")
	public String addBuses(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		model.addAttribute("types", service.getTypes());
		return "buses/add-bus";
	}
	
	@GetMapping("/buses/consult-bus")
	public String consult(Model model) {
		model.addAttribute("tmio1Bus", new Tmio1Bus());
		return "buses/consult-bus";
	}

	@PostMapping("/buses/add-bus")
	public String saveApps(@RequestParam(value = "action", required = true) String action, @Validated Tmio1Bus tmio1Bus,
			BindingResult bindingResult, Model model) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				model.addAttribute("types", service.getTypes());
				return "buses/add-bus";
			} else {
				try {
					service.save(tmio1Bus);
				} catch (SaveBusException | BusNullException | BusTypeException | TypeNullException
						| CapacityNullException e) {

					return "redirect:/error";
				}
			}
			model.addAttribute("types", service.getTypes());
		}
		return "redirect:/buses";
	}



	@PostMapping("/buses/consult-bus")
	public String showConsult(@ModelAttribute Tmio1Bus tmio1Bus, Model model) {

		Optional<Tmio1Bus> b = service.findById(tmio1Bus.getId());
		if (b.get() == null) {

		}
		model.addAttribute("tmio1Bus", b.get());
		return "buses/showConsult-bus";
	}
}
