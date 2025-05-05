package com.gatekeeperpro.controller;

import com.gatekeeperpro.model.User;
import com.gatekeeperpro.service.UserService;
import com.gatekeeperpro.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        user.setRole("USER");
        userService.save(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(body.get("username"), body.get("password"))
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(Map.of("token", token));
    }
}