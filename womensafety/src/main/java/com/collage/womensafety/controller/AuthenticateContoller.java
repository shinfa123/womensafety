package com.collage.womensafety.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collage.womensafety.domain.MyUser;
import com.collage.womensafety.model.AuthenticationRequest;
import com.collage.womensafety.model.AuthenticationResponse;
import com.collage.womensafety.model.JwtUtil;
import com.collage.womensafety.services.UserService;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticateContoller {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/authenticate")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public MyUser signUp(@RequestBody MyUser myUser) {
		return userService.saveUser(myUser);
	}
	
	@GetMapping("/getAdminUserList")
	public List<MyUser> getAdminUserList() {
		return userService.getAllUsers();
	}
	
}
