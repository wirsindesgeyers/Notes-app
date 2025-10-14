package com.kauan.br.projetinhoteste.model.user.dtos;

import com.kauan.br.projetinhoteste.model.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
