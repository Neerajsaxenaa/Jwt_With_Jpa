package com.deepmindz.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deepmindz.config.JwtTokenUtil;
import com.deepmindz.dto.ApiResponse;
import com.deepmindz.model.JwtRequest;
import com.deepmindz.model.JwtResponse;
import com.deepmindz.model.UserDTO;
import com.deepmindz.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		if (userDetails == null) {
			ApiResponse apiResponsere = new ApiResponse();
			Map<String, String> data = new HashMap<>();
			data.put("User", "Admin");
			data.put("Status", "UnAuthorized User");
			data.put("Token", "NA");
			apiResponsere.setStatus(200);
			apiResponsere.setMessage("Failure");
			apiResponsere.setData(data);
			return new ResponseEntity<>(apiResponsere, HttpStatus.OK);
		}
		final String token = jwtTokenUtil.generateToken(userDetails);

		ApiResponse apiResponsere = new ApiResponse();
		Map<String, String> data = new HashMap<>();
		data.put("User", "Admin");
		data.put("Status", "Authorized User");
		data.put("Token", "" + token);
		apiResponsere.setStatus(200);
		apiResponsere.setMessage("Hello : "+userDetails.getUsername()+ " Welcome to the dashboard..!!");
		apiResponsere.setData(data); 
		return new ResponseEntity<>(apiResponsere, HttpStatus.OK);

	}
		
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}