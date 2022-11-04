package br.com.market.users.controller;

import br.com.market.users.model.User;
import br.com.market.users.producer.UserProducer;
import br.com.market.users.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    private final UserProducer userProducer;

    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, UserProducer userProducer, ObjectMapper objectMapper) {
        this.userService = userService;
        this.userProducer = userProducer;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = userService.findAllUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findOneUser(@PathVariable Long id) {
        Optional<User> user = userService.findOneUser(id);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder)
            throws JsonProcessingException {
        User savedUser = userService.saveUser(user);

        String message = objectMapper.writeValueAsString(savedUser);

        userProducer.send(message);

        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).body(savedUser);
    }
}
