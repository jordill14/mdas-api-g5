package com.ccm.pokemon.pokemon.infrastructure.repositories;

import com.ccm.pokemon.pokemon.domain.aggregate.Pokemon;
import com.ccm.pokemon.pokemon.domain.interfaces.PokemonRepository;
import com.ccm.pokemon.pokemon.domain.valueObjects.PokemonId;
import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
@Named("inMemory")
public class InMemoryCounterPokemonRepository implements PokemonRepository {

    List<Pokemon> inMemoryPokemon = new ArrayList<>();

    @Override
    public Pokemon find(PokemonId pokemonId) {
        return this.inMemoryPokemon.stream().filter(x -> x.getPokemonId().equals(pokemonId)).findAny().orElse(null);
    }

    @Override
    public Pokemon update(Pokemon pokemon) {
        OptionalInt index = IntStream.range(0, this.inMemoryPokemon.size()).filter(i -> pokemon.getUserId().equals(this.inMemoryPokemon.get(i).getUserId())).findFirst();

        this.inMemoryPokemon.set(index.getAsInt(), pokemon);
        return pokemon;
    }

}
