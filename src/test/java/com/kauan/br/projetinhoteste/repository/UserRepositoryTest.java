package com.kauan.br.projetinhoteste.repository;

import com.kauan.br.projetinhoteste.model.user.User;
import com.kauan.br.projetinhoteste.model.user.UserRole;
import com.kauan.br.projetinhoteste.model.user.dtos.AuthenticationDTO;
import com.kauan.br.projetinhoteste.model.user.dtos.RegisterDTO;
import com.kauan.br.projetinhoteste.model.user.dtos.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    @DisplayName("Should get an user by login")
    void findByLoginCase1() {
        String login = "teste";
        UserDTO data = new UserDTO(login, UserRole.ADMIN, "teste123");

        this.createUser(data);

        Optional<User> foundUser= this.userRepository.findByLogin(login);

        assertThat(foundUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get an user by login when user does not exist")
    void findByLoginCase2() {
        String login = "teste";

        Optional<User> foundUser= this.userRepository.findByLogin(login);

        assertThat(foundUser.isEmpty()).isTrue();
    }


    private User createUser(UserDTO dto) {
        User newUser = new User(dto.login(), dto.password(), dto.role());
        this.entityManager.persist(newUser);
        return newUser;
    }

}