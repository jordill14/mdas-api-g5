package com.ccm.pokemon.pokemon.domain.services;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.pokemon.pokemon.infrastructure.httpclient.HttpPokemonClientImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class PokemonFinder {

    @Inject
    @Named("HttpPokemon")
    HttpPokemonClientImpl httpPokemonClientImpl;

    @Inject
    @Named("inMemory")
    PokemonRepository pokemonRepository;

    public Pokemon findPokemon(PokemonId pokemonId) throws PokemonNotFoundException, NetworkConnectionException, TimeoutException, UnknownException {
        if (this.pokemonRepository.exists(pokemonId)) {
            return this.pokemonRepository.find(pokemonId);
        }
        return this.httpPokemonClientImpl.find(pokemonId);
    }
}
