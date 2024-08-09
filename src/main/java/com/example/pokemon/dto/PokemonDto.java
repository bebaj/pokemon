package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PokemonDto {
    private int id;
    private String name;
    private int height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<AbilityEntry> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityEntry> abilities) {
        this.abilities = abilities;
    }

    public List<TypeEntry> getTypes() {
        return types;
    }

    public void setTypes(List<TypeEntry> types) {
        this.types = types;
    }

    private int weight;

    @JsonProperty("abilities")
    private List<AbilityEntry> abilities;

    @JsonProperty("types")
    private List<TypeEntry> types;

    // Getters and Setters

    public static class AbilityEntry {
        private Ability ability;

        // Getters and Setters

        public static class Ability {
            private String name;
            private String url;

            // Getters and Setters
        }
    }

    public static class TypeEntry {
        private Type type;

        // Getters and Setters

        public static class Type {
            private String name;
            private String url;

            // Getters and Setters
        }
    }
}
