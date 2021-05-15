package com.ccm.pokemon.pokemon.domain.aggregate;

import com.ccm.pokemon.pokemon.domain.valueObjects.*;

import java.util.Objects;

public class Pokemon {

    public Pokemon(Name name, PokemonId pokemonId) {
        this.name = name;
        this.pokemonId = pokemonId;
        this.pokemonTypes = new PokemonTypes();
        this.incrementFavorite = new CounterFavorite();
    }

    public Name getName() {
        return this.name;
    }

    public PokemonId getPokemonId() {
        return this.pokemonId;
    }

    public PokemonTypes getPokemonTypes() {
        return this.pokemonTypes;
    }

    private Name name;
    private PokemonId pokemonId;
    private PokemonTypes pokemonTypes;
    private final CounterFavorite incrementFavorite;

    public void addPokemonType(PokemonType pokemonType) {
        this.pokemonTypes.addType(pokemonType);
    }

    public void incrementCounter() {
        this.incrementFavorite.counterFavorite();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pokemon pokemon = (Pokemon) o;
        return this.name.equals(pokemon.name) && this.pokemonId.equals(pokemon.pokemonId) && this.pokemonTypes.equals(pokemon.pokemonTypes);
    }
}
