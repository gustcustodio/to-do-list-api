package com.gustcustodio.to_do_list_api.controllers;

import com.gustcustodio.to_do_list_api.dtos.LoginRequestDTO;
import com.gustcustodio.to_do_list_api.dtos.LoginResponseDTO;
import com.gustcustodio.to_do_list_api.dtos.RegisterDTO;
import com.gustcustodio.to_do_list_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/todos")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@Valid @RequestBody RegisterDTO registerDTO) {
        LoginResponseDTO loginResponseDTO = userService.register(registerDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = userService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
