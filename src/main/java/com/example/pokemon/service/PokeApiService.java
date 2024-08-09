package com.example.pokemon.service;

import com.example.pokemon.dto.PokemonDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PokeApiService {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "https://pokeapi.co/api/v2/";

    public PokeApiService() {
        this.restTemplate = new RestTemplate();
    }

    @Cacheable("pokemon")
    public PokemonDto getPokemonByName(String name) {
        String url = BASE_URL + "pokemon/" + name.toLowerCase();
        return restTemplate.getForObject(url, PokemonDto.class);
    }
}
