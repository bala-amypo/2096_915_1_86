package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    
    @Bean
    public org.springframework.boot.CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(User.builder()
                        .username("admin")
                        .name("Admin User")
                        .password(encoder.encode("admin123"))
                        .role("ADMIN")
                        .build());
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(User.builder()
                        .username("user")
                        .name("Normal User")
                        .password(encoder.encode("user123"))
                        .role("USER")
                        .build());
            }
        };
    }
}
