package com.example.pokemon.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_pokemon",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private Set<Pokemon> caughtPokemons = new HashSet<>();

    // Constructors
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Pokemon> getCaughtPokemons() {
        return caughtPokemons;
    }

    public void setCaughtPokemons(Set<Pokemon> caughtPokemons) {
        this.caughtPokemons = caughtPokemons;
    }


}
