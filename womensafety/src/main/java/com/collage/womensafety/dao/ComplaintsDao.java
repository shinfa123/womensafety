package com.collage.womensafety.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.womensafety.domain.Complaints;

public interface ComplaintsDao extends JpaRepository<Complaints, Integer>{
	 List<Complaints> findByUserId(Integer userId);
	 List<Complaints> findByUserIdAndIsNewlyUpdatedTrue(Integer userId);
	 List<Complaints> findByIsNewlyUpdatedTrue();
}
