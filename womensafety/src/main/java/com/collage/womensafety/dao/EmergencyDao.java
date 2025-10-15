package com.collage.womensafety.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.womensafety.domain.Emergency;

public interface EmergencyDao extends JpaRepository<Emergency, Integer>{

}
