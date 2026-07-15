package com.memoryjava.workshopmongo.services;

import com.memoryjava.workshopmongo.domain.Post;
import com.memoryjava.workshopmongo.repository.PostRepository;
import com.memoryjava.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;


    public Post findById(String id) {
        Post user = repository.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("Object not found.");
        }

        return user;
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
        ZoneId zone = ZoneId.of("UTC");
        maxDate = ZonedDateTime.ofInstant(maxDate, zone)
                .plusDays(1)
                .with(LocalTime.MAX) // Define para 23:59:59.999999999 do outro dia
                .toInstant();
        return repository.fullSearch(text, minDate, maxDate);
    }

}
