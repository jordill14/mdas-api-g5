package com.ccm.pokemon.pokemon.domain.interfaces;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.exceptions.NetworkConnectionException;
import com.ccm.pokemon.pokemon.domain.exceptions.TimeoutException;
import com.ccm.pokemon.pokemon.domain.exceptions.UnknownException;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.pokemon.pokemon.domain.exceptions.PokemonNotFoundException;
import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.vo.UserId;

public interface PokemonRepository {

    public Pokemon find(PokemonId pokemonId);

    public Pokemon update(Pokemon Pokemon);

    public boolean exists(PokemonId pokemonId);

    public void create(Pokemon Pokemon);

}
