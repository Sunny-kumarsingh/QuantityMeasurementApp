package com.quantitymeasurement.quantitymeasurementapp.controller;

import com.quantitymeasurement.quantitymeasurementapp.dto.dtoResponse.AuthResponse;
import com.quantitymeasurement.quantitymeasurementapp.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/google")
public class GoogleAuthController {
	
	@Autowired
	private GoogleAuthService googleAuthService;

	@GetMapping("/callback")
	public ResponseEntity<AuthResponse> handleGoogleCallback(@RequestParam String code){
		return ResponseEntity.ok(googleAuthService.handleGoogleAuth(code));
	}
}