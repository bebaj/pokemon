package com.example.pokemon.service;

import com.example.pokemon.model.Pokemon;
import java.util.Optional;

public interface PokemonService {
    Optional<Pokemon> findById(Long id);
    Optional<Pokemon> findByName(String name);
    Pokemon save(Pokemon pokemon);
}