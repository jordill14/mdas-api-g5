package com.ccm.pokemon.pokemon.infrastructure.repositories;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

@ApplicationScoped
@Named("inMemory")
public class InMemoryPokemonRepository implements PokemonRepository {

    List<Pokemon> inMemoryPokemon = new ArrayList<>();

    @Override
    public Pokemon find(PokemonId pokemonId) {
        return this.inMemoryPokemon.stream().filter(x -> x.getPokemonId().equals(pokemonId)).findAny().orElse(null);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        OptionalInt index = IntStream.range(0, this.inMemoryPokemon.size()).filter(i -> pokemon.getPokemonId().equals(this.inMemoryPokemon.get(i).getPokemonId())).findFirst();

        this.inMemoryPokemon.set(index.getAsInt(), pokemon);
        return pokemon;
    }

    @Override
    public boolean exists(PokemonId pokemonId) {
        return this.inMemoryPokemon.stream().anyMatch(x -> x.getPokemonId().equals(pokemonId));
    }

    @Override
    public void create(Pokemon pokemon) {
        this.inMemoryPokemon.add(pokemon);
    }

}
