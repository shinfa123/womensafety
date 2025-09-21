package com.collage.womensafety.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collage.womensafety.dao.ComplaintsDao;
import com.collage.womensafety.domain.Complaints;
import com.collage.womensafety.domain.MyUser;

@Service
public class ComplaintsService {
	@Autowired
	private ComplaintsDao complaintsDao;
	
	@Autowired
	private UserService userService;
	
	public Complaints saveComplaints(Complaints complaints) {
		MyUser myUser = userService.getUserById(complaints.getUser().getId());
		if (myUser != null) {
			complaints.setUser(myUser);
		}
		if(complaints.isNewlyUpdated()) {
			complaints.setNewlyUpdatedForAdmin(true);
		}
		return complaintsDao.save(complaints);
	}
	
	public ArrayList<Complaints> saveComplaintsList(ArrayList<Complaints> complaints) {
		for (Complaints complaint : complaints) {
			if (complaint.isNewlyUpdated()) {
				complaint.setNewlyUpdatedForAdmin(true);
			}
		}
		return (ArrayList<Complaints>) complaintsDao.saveAll(complaints);
	}
	
	public ArrayList<Complaints> getAllComplaintsList() {
		return (ArrayList<Complaints>) complaintsDao.findAll();
	}
	
	public ArrayList<Complaints> getAllComplaintsListByUser(Integer userId) {
		return (ArrayList<Complaints>) complaintsDao.findByUserId(userId);
	}
	
	public ArrayList<Complaints> getNewlyEditedComplaintsList(Integer userId) {
		ArrayList<Complaints> complaintsList = new ArrayList<>();
		MyUser myUser = userService.getUserById(userId);
		if (myUser.isAdmin()) {
			complaintsList = (ArrayList<Complaints>) complaintsDao.findByIsNewlyUpdatedForAdminTrue();
		} else {
			complaintsList = (ArrayList<Complaints>) complaintsDao.findByUserIdAndIsNewlyUpdatedTrue(userId);
		}
		return complaintsList;
	}
	
	public void updateNotifications(Integer userId) {
		MyUser myUser = userService.getUserById(userId);
		if (myUser.isAdmin()) {
			List<Complaints> complaintsList = (ArrayList<Complaints>) complaintsDao.findAll();
			complaintsList.forEach(complaint -> complaint.setNewlyUpdatedForAdmin(false));
			complaintsDao.saveAll(complaintsList);
		} else {
			List<Complaints> complaintsList = complaintsDao.findByUserId(userId);
			complaintsList.forEach(complaint -> complaint.setNewlyUpdated(false));
			complaintsDao.saveAll(complaintsList);
		}

	}
	
}
