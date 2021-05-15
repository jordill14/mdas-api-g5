package com.ccm.pokemon.pokemon.domain.valueObjects;

public class CounterFavorite {

    public CounterFavorite() {
        this.number = 0;
    }

    public void counterFavorite() {
        this.number++;
    }

    private int number;

}
