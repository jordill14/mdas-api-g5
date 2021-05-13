package com.ccm.user.user.domain.vo;

import com.ccm.user.user.domain.entities.FavouritePokemon;
import com.ccm.user.user.domain.entities.MessageQueue;
import com.ccm.user.user.domain.exceptions.FavouritePokemonAlreadyExistsException;
import com.ccm.user.user.domain.exceptions.FavouritePokemonDoesNotExistException;
import com.ccm.user.user.domain.interfaces.SendInterface;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class FavouritePokemons {

    public FavouritePokemons() {
        this.favouritePokemonList = new ArrayList<>();
    }

    private final List<FavouritePokemon> favouritePokemonList;

    @Inject
    @Named("send")
    private SendInterface sendInterface;

    public void addFavouritePokemonToList(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        existsGuard(pokemon);
        this.sendInterface.main(new MessageQueue(String.valueOf(pokemon.getFavouritePokemonId().getPokemonId())));
        this.favouritePokemonList.add(pokemon);
    }

    public void removeFavouritePokemonFromList(FavouritePokemon pokemon) throws FavouritePokemonDoesNotExistException {
        notExistsGuard(pokemon);
        OptionalInt index =
                IntStream.range(0, this.favouritePokemonList.size()).filter(i -> pokemon.getFavouritePokemonId().equals(this.favouritePokemonList.get(i).getFavouritePokemonId())).findFirst();
        this.favouritePokemonList.remove(index.getAsInt());
    }

    public List<FavouritePokemon> getFavouritePokemonList() {
        return this.favouritePokemonList;
    }

    private void existsGuard(FavouritePokemon pokemon) throws FavouritePokemonAlreadyExistsException {
        FavouritePokemonId pokemonId = pokemon.getFavouritePokemonId();

        if (this.favouritePokemonList.stream().anyMatch(favouritePokemon -> favouritePokemon.getFavouritePokemonId().equals(pokemonId))) {
            throw new FavouritePokemonAlreadyExistsException("The user already has the pokemon " + pokemonId.getPokemonId() + " as favourite");
        }
    }

    private void notExistsGuard(FavouritePokemon pokemon) throws FavouritePokemonDoesNotExistException {
        FavouritePokemonId pokemonId = pokemon.getFavouritePokemonId();

        if (!this.favouritePokemonList.stream().anyMatch(favouritePokemon -> favouritePokemon.getFavouritePokemonId().equals(pokemonId))) {
            throw new FavouritePokemonDoesNotExistException("The user doesn't have the pokemon " + pokemonId.getPokemonId() + " as favourite");
        }
    }

}
