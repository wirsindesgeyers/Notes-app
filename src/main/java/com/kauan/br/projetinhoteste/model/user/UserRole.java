package com.kauan.br.projetinhoteste.model.user;

public enum UserRole {
    ADMIN ("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }
}
