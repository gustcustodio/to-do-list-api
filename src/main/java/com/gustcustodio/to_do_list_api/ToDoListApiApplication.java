package com.gustcustodio.to_do_list_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ToDoListApiApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    public ToDoListApiApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //		System.out.println("ENCODE = " + passwordEncoder.encode("123456"));
    }

}
