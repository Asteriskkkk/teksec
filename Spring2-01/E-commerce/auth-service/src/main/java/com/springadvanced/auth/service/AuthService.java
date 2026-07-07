package com.springadvanced.auth.service;

import com.springadvanced.auth.dto.AuthRequest;
import com.springadvanced.auth.entity.AppUser;
import com.springadvanced.auth.exception.InvalidCredentialsException;
import com.springadvanced.auth.exception.UserAlreadyExistsException;
import com.springadvanced.auth.repository.UserRepository;
import com.springadvanced.auth.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setRole("USER");
        userRepository.save(appUser);
    }

    public String login(AuthRequest request) {
        AppUser appUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), appUser.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return jwtService.generateToken(appUser.getUsername());
    }
}
