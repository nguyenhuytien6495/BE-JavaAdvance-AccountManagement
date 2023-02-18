package com.huytien.controller;

import com.huytien.dto.LoginInfoDto;
import com.huytien.entity.Account;
import com.huytien.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "api/v1/auth")
public class AuthController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IAccountService service;

	@GetMapping("/login")
	public LoginInfoDto login(Principal principal) {

		String username = principal.getName();
		Account entity = service.getAccountByUsername(username);

		// convert entity --> dto
		LoginInfoDto dto = modelMapper.map(entity, LoginInfoDto.class);

		return dto;
	}
}
