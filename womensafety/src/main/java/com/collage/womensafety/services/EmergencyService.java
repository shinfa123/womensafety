package com.collage.womensafety.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collage.womensafety.dao.EmergencyDao;
import com.collage.womensafety.domain.Complaints;
import com.collage.womensafety.domain.Emergency;
@Service
public class EmergencyService {

	@Autowired
	private EmergencyDao emergencyDao;
	
	public Emergency saveEmergency(Emergency emergency) {
		return emergencyDao.save(emergency);
	}
	
	public ArrayList<Emergency> getAllEmergencyList() {
		return (ArrayList<Emergency>) emergencyDao.findAll();
	}
	
	public ArrayList<Emergency> saveEmergencyList(ArrayList<Emergency> emergency) {
		return (ArrayList<Emergency>) emergencyDao.saveAll(emergency);
	}
}
