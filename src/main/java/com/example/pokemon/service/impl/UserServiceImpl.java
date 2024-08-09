package com.example.pokemon.service.impl;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.User;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.repository.UserRepository;
import com.example.pokemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PokemonRepository pokemonRepository) {
        this.userRepository = userRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void addPokemonToUser(User user, Long pokemonId) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if (pokemon.isPresent()) {
            user.getCaughtPokemons().add(pokemon.get());
            userRepository.save(user);
        } else {
            throw new RuntimeException("Pokemon not found with id: " + pokemonId);
        }
    }

    @Override
    public void removePokemonFromUser(User user, Long pokemonId) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if (pokemon.isPresent()) {
            user.getCaughtPokemons().remove(pokemon.get());
            userRepository.save(user);
        } else {
            throw new RuntimeException("Pokemon not found with id: " + pokemonId);
        }
    }
}
