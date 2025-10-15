package com.collage.womensafety.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collage.womensafety.domain.Complaints;
import com.collage.womensafety.domain.Emergency;
import com.collage.womensafety.services.EmergencyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmergencyController {
	
	@Autowired
	private EmergencyService emergencyService;
	
	@PostMapping("/saveEmergency")
	public Emergency saveEmergency(@RequestBody Emergency emergency) {
		return emergencyService.saveEmergency(emergency);
	}
	
	@GetMapping("/getEmergencyList")
	public List<Emergency> getEmergencyList() {
		return emergencyService.getAllEmergencyList();
	}
	
	@PostMapping("/saveAllEmergency")
	public List<Emergency> saveAllEmergency(@RequestBody ArrayList<Emergency> emergency) {
		return emergencyService.saveEmergencyList(emergency);
	}
}
