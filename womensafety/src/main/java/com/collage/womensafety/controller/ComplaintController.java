package com.collage.womensafety.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collage.womensafety.domain.Complaints;
import com.collage.womensafety.services.ComplaintsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ComplaintController {
	@Autowired
	private ComplaintsService complaintsService;
	
	@PostMapping("/saveComplaints")
	public Complaints saveComplaints(@RequestBody Complaints complaints) {
		return complaintsService.saveComplaints(complaints);
	}
	
	@PostMapping("/saveAllComplaints")
	public List<Complaints> saveAllComplaints(@RequestBody ArrayList<Complaints> complaints) {
		return complaintsService.saveComplaintsList(complaints);
	}
	
	@GetMapping("/getComplaintsList")
	public List<Complaints> getComplaintsList() {
		return complaintsService.getAllComplaintsList();
	}
	
	@GetMapping("/getComplaintsListByUser/{userId}")
	public List<Complaints> getComplaintsListByUser(@PathVariable Integer userId) {
	    return complaintsService.getAllComplaintsListByUser(userId);
	}
	
	@GetMapping("/getNewlyEditedComplaintsList/{userId}")
	public List<Complaints> getNewlyEditedComplaintsList(@PathVariable Integer userId) {
	    return complaintsService.getNewlyEditedComplaintsList(userId);
	}

	@PutMapping("/updateNotifications/{userId}")
	public void updateNotifications(@PathVariable Integer userId) {
	    complaintsService.updateNotifications(userId);
	}
}
