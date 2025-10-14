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

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return user;
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