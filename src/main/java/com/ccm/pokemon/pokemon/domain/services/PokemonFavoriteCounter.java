package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.inject.Inject;
import javax.inject.Named;
import java.nio.file.Files;

public class PokemonFavoriteCounter {

    @Inject
    @Named("inMemory")
    PokemonRepository pokemonRepository;

    public Pokemon counter(PokemonId pokemonId) throws PokemonNotFoundException, TimeoutException, UnknownException, NetworkConnectionException {

        Pokemon pokemon = this.pokemonRepository.find(pokemonId);
        pokemon.incrementCounter();
        this.pokemonRepository.update(pokemon);

    }
}
