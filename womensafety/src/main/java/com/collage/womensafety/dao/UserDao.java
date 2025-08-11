package com.collage.womensafety.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.collage.womensafety.domain.MyUser;

public interface UserDao extends JpaRepository<MyUser, Integer> {
	Optional<MyUser> findByUserName(String userName);

	Boolean existsByUserName(String userName);
}
