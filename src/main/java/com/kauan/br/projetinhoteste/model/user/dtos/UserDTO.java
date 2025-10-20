package com.kauan.br.projetinhoteste.model.user.dtos;

import com.kauan.br.projetinhoteste.model.user.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UserDTO (


        String login,

        UserRole role,
        String password

) {

}

