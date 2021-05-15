package com.ccm.pokemon.pokemon.application.useCases;

import com.ccm.pokemon.pokemon.domain.services.PokemonFavoriteCounter;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IncrementCountFavorites {

    @Inject
    PokemonFavoriteCounter pokemonFavoriteCounter;

    public void counterFavorite(int pokemon) {
        this.pokemonFavoriteCounter.counter(new PokemonId(pokemon));
    }

}
