package com.gustcustodio.to_do_list_api.services;

import com.gustcustodio.to_do_list_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
