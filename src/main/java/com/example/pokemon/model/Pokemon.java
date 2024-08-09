package com.example.pokemon.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Id
    private Long id; // Matches PokeAPI's Pokémon ID

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "caughtPokemons")
    private Set<User> users = new HashSet<>();

    // Additional fields as needed (e.g., height, weight)

    // Getters and Setters
}
