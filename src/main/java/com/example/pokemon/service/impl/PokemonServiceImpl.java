package com.example.pokemon.service.impl;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Optional<Pokemon> findById(Long id) {
        return pokemonRepository.findById(id);
    }

    @Override
    public Optional<Pokemon> findByName(String name) {
        return pokemonRepository.findByName(name);
    }

    @Override
    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }
}
