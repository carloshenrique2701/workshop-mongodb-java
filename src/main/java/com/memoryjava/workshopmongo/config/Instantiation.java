package com.memoryjava.workshopmongo.config;

import com.memoryjava.workshopmongo.domain.Post;
import com.memoryjava.workshopmongo.domain.User;
import com.memoryjava.workshopmongo.repository.PostRepository;
import com.memoryjava.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, Instant.parse("2026-02-26T01:30:26Z"), "Partiu Viagem", "Vou viajar para a Bahia", maria);
        Post post2 = new Post(null, Instant.parse("2026-03-01T22:04:55Z"), "Boa noite", "Vou puchar um ronco", alex);

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
