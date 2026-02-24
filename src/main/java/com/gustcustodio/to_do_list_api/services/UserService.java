package com.gustcustodio.to_do_list_api.services;

import com.gustcustodio.to_do_list_api.dtos.LoginRequestDTO;
import com.gustcustodio.to_do_list_api.dtos.LoginResponseDTO;
import com.gustcustodio.to_do_list_api.dtos.RegisterDTO;
import com.gustcustodio.to_do_list_api.entities.User;
import com.gustcustodio.to_do_list_api.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(TokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseDTO register(RegisterDTO registerDTO) {
        if (userRepository.findByEmail(registerDTO.email()) != null) throw new RuntimeException();

        String encryptedPassword = passwordEncoder.encode(registerDTO.password());
        User newUser = new User(registerDTO.name(), registerDTO.email(), encryptedPassword);
        newUser = userRepository.save(newUser);

        LoginResponseDTO loginResponseDTO = login(new LoginRequestDTO(newUser.getEmail(), registerDTO.password()));

        return loginResponseDTO;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var user = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
        var auth = authenticationManager.authenticate(user);
        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    protected User authenticated() {
        try {
            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username not found");
        }
    }

}
