package com.manoj.cda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manoj.cda.dto.LoginRequestDTO;
import com.manoj.cda.dto.LoginResponseDTO;
import com.manoj.cda.response_structure.ResponseStructure;
import com.manoj.cda.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController 
{
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}

  