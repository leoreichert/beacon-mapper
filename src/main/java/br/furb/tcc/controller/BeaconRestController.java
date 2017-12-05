package br.furb.tcc.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.furb.tcc.model.Beacon;
import br.furb.tcc.model.BeaconAccess;
import br.furb.tcc.repository.BeaconAccessRepository;
import br.furb.tcc.repository.BeaconRepository;

@RestController
public class BeaconRestController {
	
	@Autowired
	BeaconRepository beaconRepository;
	
	@Autowired
	BeaconAccessRepository beaconAccessRepository;
	
	private String retorno = "";
	private int maxValue = 0;
	
	@Transactional
	@RequestMapping(value = "/AccessBeacon", method = RequestMethod.POST)
	public String newAccessBeacon(@RequestParam String uid) {
		Optional<Beacon> beacon = beaconRepository.findByUid(uid);
		
		if (beacon.isPresent()) {
			BeaconAccess beaconAccess = new BeaconAccess();
			beaconAccess.setDateAccess(Date.valueOf(LocalDate.now()));
			beaconAccess.setTimeAccess(Time.valueOf(LocalTime.now()));
			beaconAccess.setIdBeacon(beacon.get());
			
			beaconAccessRepository.save(beaconAccess);
			
			return "{\"resposta\" : \"Ok\"}";
		}
		
		return "{\"resposta\" : \"Url sem vínculo a Beacon\"}";
	}
	
	@Transactional
	@RequestMapping(value = "/UpdateTemplate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateTemplate(@RequestParam String uid, @RequestBody String model) {
		Optional<Beacon> beacon = beaconRepository.findByUid(uid);
		
		if (beacon.isPresent()) {
			Beacon beaconUpdate = beacon.get();
			beaconUpdate.setTemplateModel(model);
			
			beaconRepository.save(beaconUpdate);
			
			return "{\"resposta\" : \"Ok\"}";
		}
		
		return "{\"resposta\" : \"UID sem vínculo a Beacon\"}";
	}
	
	@Transactional
	@RequestMapping(value = "/GetTemplate", method = RequestMethod.GET)
	public String getTemplate(@RequestParam String uid) {
		Optional<Beacon> beacon = beaconRepository.findByUid(uid);
		
		if (beacon.isPresent())			
			return beacon.get().getTemplateModel();
		
		return "{\"resposta\" : \"none\"}";
	}
	
	@Transactional
	@RequestMapping(value = "/GetAllAccessBeacon", method = RequestMethod.GET)
	public String getAllAccessBeacon() {
		String baseFormat = 
				"{ \"posX\" : \"%d\", "
				+ "\"posY\" : \"%d\", "
				+ "\"value\" : \"%d\" },";
	
		beaconRepository.findAll().forEach(beacon -> {
			List<BeaconAccess> allAccess = beaconAccessRepository.findByIdBeacon(beacon);
			
			int access = allAccess.size();
			if (maxValue < access)
				maxValue = access;
			
			retorno += String.format(baseFormat, beacon.getPosicaoX() * 2, beacon.getPosicaoY() * 2, access);
		});
		
		String retornoLocal = "{\"max\" : \"" + maxValue + "\", \"access\" : [ " + retorno.substring(0, retorno.length() - 1) + "]}";
		retorno = "";
		maxValue = 0;
		
		return retornoLocal;	
	}

}
