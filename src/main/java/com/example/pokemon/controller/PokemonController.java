package com.example.pokemon.controller;

import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.User;
import com.example.pokemon.service.PokeApiService;
import com.example.pokemon.service.PokemonService;
import com.example.pokemon.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokeApiService pokeApiService;
    private final PokemonService pokemonService;
    private final UserService userService;

    public PokemonController(PokeApiService pokeApiService, PokemonService pokemonService, UserService userService) {
        this.pokeApiService = pokeApiService;
        this.pokemonService = pokemonService;
        this.userService = userService;
    }

    @PostMapping("/catch-pokemon/{pokemonId}")
    public ResponseEntity<String> catchPokemon(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long pokemonId) {
        Optional<User> userOptional = userService.findByUsername(userDetails.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.addPokemonToUser(user, pokemonId);
            return ResponseEntity.ok("Pokemon caught successfully!");
        } else {
            return ResponseEntity.status(403).body("User not found");
        }
    }

    @DeleteMapping("/release-pokemon/{pokemonId}")
    public ResponseEntity<String> releasePokemon(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long pokemonId) {
        Optional<User> userOptional = userService.findByUsername(userDetails.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userService.removePokemonFromUser(user, pokemonId);
            return ResponseEntity.ok("Pokemon released successfully!");
        } else {
            return ResponseEntity.status(403).body("User not found");
        }
    }
}
