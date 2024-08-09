package com.example.pokemon.controller;

import com.example.pokemon.model.User;
import com.example.pokemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/catch-pokemon/{pokemonId}")
    public ResponseEntity<String> catchPokemon(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long pokemonId) {
        Optional<User> user = userService.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            userService.addPokemonToUser(user.get(), pokemonId);
            return ResponseEntity.ok("Pokemon caught successfully!");
        } else {
            return ResponseEntity.status(403).body("User not found");
        }
    }

    @DeleteMapping("/release-pokemon/{pokemonId}")
    public ResponseEntity<String> releasePokemon(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long pokemonId) {
        Optional<User> user = userService.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            userService.removePokemonFromUser(user.get(), pokemonId);
            return ResponseEntity.ok("Pokemon released successfully!");
        } else {
            return ResponseEntity.status(403).body("User not found");
        }
    }
}
