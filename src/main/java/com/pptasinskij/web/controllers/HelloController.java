package com.pptasinskij.web.controllers;

import com.pptasinskij.web.domain.entities.UserNotFoundException;
import com.pptasinskij.web.domain.entities.user.User;
import com.pptasinskij.web.domain.entities.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RequestMapping("/users")
public class HelloController {

    private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping(value = "/image", produces = IMAGE_JPEG_VALUE)
    public Resource getImageAsResource() {
        return new ClassPathResource("img_lights.jpg");
    }

}
