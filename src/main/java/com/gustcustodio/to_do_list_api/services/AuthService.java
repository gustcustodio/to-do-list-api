package com.gustcustodio.to_do_list_api.services;

import com.gustcustodio.to_do_list_api.entities.User;
import com.gustcustodio.to_do_list_api.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateUser(Long userId) {
        User currentUser = userService.authenticated();
        if (!currentUser.getId().equals(userId)) {
            throw new ForbiddenException("Forbidden");
        }
    }

}
