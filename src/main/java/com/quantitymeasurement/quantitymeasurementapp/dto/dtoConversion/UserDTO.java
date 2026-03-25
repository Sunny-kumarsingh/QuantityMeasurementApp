package com.quantitymeasurement.quantitymeasurementapp.dto.dtoConversion;

import com.quantitymeasurement.quantitymeasurementapp.dto.dtoRequest.RegisterRequest;
import com.quantitymeasurement.quantitymeasurementapp.entity.User;

public class UserDTO {
	public User toUser(RegisterRequest registerRequest){
		return new User(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getRole());
	}
}