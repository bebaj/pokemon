package com.example.pokemon.controller;
import com.example.pokemon.model.User;
import com.example.pokemon.repository.UserRepository;
import com.example.pokemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{username}/pokemons/{pokemonId}")
    public ResponseEntity<Void> addPokemonToUser(@PathVariable String username, @PathVariable Long pokemonId) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            userService.addPokemonToUser(user.get(), pokemonId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}/pokemons/{pokemonId}")
    public ResponseEntity<Void> removePokemonFromUser(@PathVariable String username, @PathVariable Long pokemonId) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            userService.removePokemonFromUser(user.get(), pokemonId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
