package com.example.pokemon.service;

import com.example.pokemon.model.Pokemon;
import com.example.pokemon.model.User;
import com.example.pokemon.repository.PokemonRepository;
import com.example.pokemon.repository.UserRepository;
import com.example.pokemon.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPokemonToUser() {
        // Given
        User user = new User();
        user.setUsername("testuser");

        Pokemon pokemon = new Pokemon();
        pokemon.setId(1L);
        pokemon.setName("pikachu");

        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemon));
        when(userRepository.save(user)).thenReturn(user);

        // When
        userService.addPokemonToUser(user, 1L);

        // Then
        assertEquals(1, user.getCaughtPokemons().size());
        verify(userRepository, times(1)).save(user);
    }
}
