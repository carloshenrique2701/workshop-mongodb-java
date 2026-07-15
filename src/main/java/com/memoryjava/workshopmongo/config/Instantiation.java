package com.memoryjava.workshopmongo.config;

import com.memoryjava.workshopmongo.domain.Post;
import com.memoryjava.workshopmongo.domain.User;
import com.memoryjava.workshopmongo.dto.AuthorDTO;
import com.memoryjava.workshopmongo.dto.CommentDTO;
import com.memoryjava.workshopmongo.repository.PostRepository;
import com.memoryjava.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.xml.stream.events.Comment;
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

        User maria = new User(null, "Maria Liz", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, Instant.parse("2026-02-26T01:30:26Z"), "Partiu Viagem", "Vou viajar para a Bahia", new AuthorDTO(maria));
        Post post2 = new Post(null, Instant.parse("2026-08-01T22:04:55Z"), "Boa noite", "Vou puxar um ronco", new AuthorDTO(alex));

        CommentDTO com1 = new CommentDTO("Boa viagem mana!", Instant.now(), new AuthorDTO(bob));
        CommentDTO com2 = new CommentDTO("Aproveite!", Instant.parse("2026-03-03T11:34:15Z"), new AuthorDTO(alex));
        CommentDTO com3 = new CommentDTO("Tenha um ótimo sono!", Instant.parse("2026-03-01T23:04:55Z"), new AuthorDTO(maria));

        post1.getComments().addAll(Arrays.asList(com1, com2));
        post2.getComments().add(com3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().add(post1);
        alex.getPosts().add(post2);

        userRepository.saveAll(Arrays.asList(maria, alex));


    }
}
