package com.memoryjava.workshopmongo.services;

import com.memoryjava.workshopmongo.domain.Post;
import com.memoryjava.workshopmongo.domain.User;
import com.memoryjava.workshopmongo.dto.UserDTO;
import com.memoryjava.workshopmongo.repository.PostRepository;
import com.memoryjava.workshopmongo.repository.UserRepository;
import com.memoryjava.workshopmongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.findByTitleContainingIgnoreCase(text);
    }

}
