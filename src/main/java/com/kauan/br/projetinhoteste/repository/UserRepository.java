package com.kauan.br.projetinhoteste.repository;

import com.kauan.br.projetinhoteste.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findByLogin(String login);

}
