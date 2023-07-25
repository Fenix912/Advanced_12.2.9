package com.reactive.util;

import com.reactive.model.User;
import com.reactive.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitData {
    private final UserRepository userRepository;

    @PostConstruct
    void init() {
        userRepository.deleteAll().block();

        for (int i = 0; i < 97; i++) {
            Mono<User> savedUser = userRepository.save(generateRandomUser());
            savedUser.block();
        }
    }

    private User generateRandomUser() {
        String randomId = UUID.randomUUID().toString();
        String randomEmail = "email" + randomId + "@gmail.com";
        return new User(randomId, randomEmail);
    }
}
