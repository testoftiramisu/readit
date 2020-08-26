package io.testoftiramisu.readit.controller;

import io.testoftiramisu.readit.dto.AuthenticationResponse;
import io.testoftiramisu.readit.dto.LoginRequest;
import io.testoftiramisu.readit.dto.RegisterRequest;
import io.testoftiramisu.readit.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("signup")
  public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
    authService.signup(registerRequest);
    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
}
