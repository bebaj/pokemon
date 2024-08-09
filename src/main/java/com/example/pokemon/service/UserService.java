package com.example.pokemon.service;

import com.example.pokemon.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User save(User user);

    void addPokemonToUser(User user, Long pokemonId);

    void removePokemonFromUser(User user, Long pokemonId);
}
