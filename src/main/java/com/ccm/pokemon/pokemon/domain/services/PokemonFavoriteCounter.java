package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class PokemonFavoriteCounter {

    @Inject
    @Named("inMemory")
    PokemonRepository pokemonRepository;

    @Inject
    PokemonFinder pokemonFinder;

    public Pokemon counter(PokemonId pokemonId) {

        Pokemon pokemon = null;
        try {
            pokemon = this.pokemonFinder.findPokemon(pokemonId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.pokemonRepository.exists(pokemonId)) {
            pokemon = this.pokemonRepository.find(pokemonId);
            pokemon.incrementCounter();
            return this.pokemonRepository.update(pokemon);

        }
        this.pokemonRepository.create(pokemon);
        pokemon.incrementCounter();
        return this.pokemonRepository.update(pokemon);

    }
}
