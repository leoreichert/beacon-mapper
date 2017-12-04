package br.furb.tcc.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.furb.tcc.model.Beacon;
import br.furb.tcc.repository.BeaconRepository;
import br.furb.tcc.validator.BeaconFormValidator;

@Controller
public class BeaconController {

	private final Logger logger = LoggerFactory.getLogger(BeaconController.class);

	@Autowired
	BeaconRepository beaconRepository;
	
	@Autowired
	BeaconFormValidator beaconFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(beaconFormValidator);
	}

	// list page
	@RequestMapping(value = "/beacons", method = RequestMethod.GET)
	public String showAllBeacons(Model model) {

		logger.debug("showAllBeacons()");
		model.addAttribute("beacons", beaconRepository.findAll());
		return "beacons/list";
	}

	// save or update beacon
	@RequestMapping(value = "/beacons", method = RequestMethod.POST)
	public String saveOrUpdatebeacon(@ModelAttribute Beacon beacon, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.debug("saveOrUpdateBeacon() : {}", beacon);

		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "beacons/beaconform";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if (beacon.getId() == null) {
				redirectAttributes.addFlashAttribute("msg", "Beacon added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Beacon updated successfully!");
			}

			beaconRepository.save(beacon);

			// POST/REDIRECT/GET
			return "redirect:/beacons/" + beacon.getId();

			// POST/FORWARD/GET
			// return "beacon/list";

		}

	}

	// show add beacon form
	@RequestMapping(value = "/beacons/add", method = RequestMethod.GET)
	public String showAddbeaconForm(Model model) {

		logger.debug("showAddbeaconForm()");

		Beacon beacon = new Beacon();
		model.addAttribute("beaconForm", beacon);
		
		populateDefaultModel(model);

		return "beacons/beaconform";

	}

	// show update form
	@RequestMapping(value = "/beacons/{id}/update", method = RequestMethod.GET)
	public String showUpdateBeaconForm(@PathVariable long id, Model model) {

		logger.debug("showUpdatebeaconForm() : {}", id);

		Beacon beacon = beaconRepository.findOne(id);
		model.addAttribute("beaconForm", beacon);
		
		populateDefaultModel(model);

		return "beacons/beaconform";

	}

	// delete beacon
	@RequestMapping(value = "/beacons/{id}/delete", method = RequestMethod.POST)
	public String deletebeacon(@PathVariable long id, final RedirectAttributes redirectAttributes) {

		logger.debug("deletebeacon() : {}", id);

		beaconRepository.delete(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "beacon is deleted!");

		return "redirect:/beacons";

	}

	// show beacon
	@RequestMapping(value = "/beacons/{id}", method = RequestMethod.GET)
	public String showbeacon(@PathVariable long id, Model model) {

		logger.debug("showbeacon() id: {}", id);

		Beacon beacon = beaconRepository.findOne(id);
		if (beacon == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "beacon not found");
		}
		model.addAttribute("beacon", beacon);

		return "beacons/show";

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		model.setViewName("beacon/show");
		model.addObject("msg", "beacon not found");

		return model;

	}
	
	@RequestMapping(value = "/mapaCalor", method = RequestMethod.GET)
	public String mapaCalor(Model model) {
		return "mapaCalor/mapaCalor";
	}
	
	private void populateDefaultModel(Model model) {
		Map<String, String> estados = new LinkedHashMap<String, String>();
		estados.put("A", "Ativo");
		estados.put("I", "Inativo");
		model.addAttribute("estadoList", estados);

	}
}