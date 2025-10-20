package com.kauan.br.projetinhoteste.services;

import com.kauan.br.projetinhoteste.model.user.dtos.RegisterDTO;
import com.kauan.br.projetinhoteste.model.user.User;
import com.kauan.br.projetinhoteste.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByLogin(username);

        return userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    public void register(RegisterDTO dto){
        if(userRepository.findByLogin(dto.login()) != null){
            throw new IllegalStateException("Usuário já existe");
        }

        String encryptedPassword = passwordEncoder.encode(dto.password());

        User newUser = new User(dto.login(), encryptedPassword, dto.role());

        userRepository.save(newUser);
    }
}