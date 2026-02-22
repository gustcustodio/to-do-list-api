package com.gustcustodio.to_do_list_api.entities;

public enum UserRole {

    USER("USER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
