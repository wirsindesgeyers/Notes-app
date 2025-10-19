package com.kauan.br.projetinhoteste.controller;

import com.kauan.br.projetinhoteste.model.user.dtos.AuthenticationDTO;
import com.kauan.br.projetinhoteste.model.user.dtos.LoginResponseDTO;
import com.kauan.br.projetinhoteste.model.user.dtos.RegisterDTO;
import com.kauan.br.projetinhoteste.model.user.User;
import com.kauan.br.projetinhoteste.services.AuthorizationService;
import com.kauan.br.projetinhoteste.services.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO dto){
        authorizationService.register(dto);
        return ResponseEntity.ok().build();
    }
}