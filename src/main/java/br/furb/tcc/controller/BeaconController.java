package br.furb.tcc.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	BeaconRepository beaconRepository;
	
	@Autowired
	BeaconFormValidator beaconFormValidator;

	@InitBinder("beaconForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(beaconFormValidator);
	}

	@RequestMapping(value = "/beacons", method = RequestMethod.GET)
	public String showAllBeacons(Model model) {
		model.addAttribute("beacons", beaconRepository.findAll());
		return "beacons/list";
	}

	@RequestMapping(value = "/beacons", method = RequestMethod.POST)
	public String saveOrUpdatebeacon(@ModelAttribute Beacon beacon, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			populateDefaultModel(model);
			return "beacons/beaconform";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if (beacon.getId() == null) {
				redirectAttributes.addFlashAttribute("msg", "Beacon adicionado com sucesso!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Beacon atualizado com sucesso!");
			}

			beaconRepository.save(beacon);
			return "redirect:/beacons/" + beacon.getId();
		}
	}

	@RequestMapping(value = "/beacons/add", method = RequestMethod.GET)
	public String showAddbeaconForm(Model model) {
		Beacon beacon = new Beacon();
		model.addAttribute("beaconForm", beacon);
		
		populateDefaultModel(model);

		return "beacons/beaconform";
	}

	@RequestMapping(value = "/beacons/{id}/update", method = RequestMethod.GET)
	public String showUpdateBeaconForm(@PathVariable long id, Model model) {
		Beacon beacon = beaconRepository.findOne(id);
		model.addAttribute("beaconForm", beacon);
		
		populateDefaultModel(model);

		return "beacons/beaconform";

	}

	@RequestMapping(value = "/beacons/{id}/delete", method = RequestMethod.POST)
	public String deletebeacon(@PathVariable long id, final RedirectAttributes redirectAttributes) {
		beaconRepository.delete(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "beacon foi apagado!");

		return "redirect:/beacons";

	}

	@RequestMapping(value = "/beacons/{id}", method = RequestMethod.GET)
	public String showbeacon(@PathVariable long id, Model model) {
		Beacon beacon = beaconRepository.findOne(id);
		if (beacon == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "beacon não encontrado!");
		}
		model.addAttribute("beacon", beacon);

		return "beacons/show";

	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {
		ModelAndView model = new ModelAndView();
		model.setViewName("beacon/show");
		model.addObject("msg", "beacon não encontrado!");

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