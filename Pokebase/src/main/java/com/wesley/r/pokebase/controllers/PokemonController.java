package com.wesley.r.pokebase.controllers;

import com.wesley.r.pokebase.classes.Pokemon;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class PokemonController {

    private ArrayList<Pokemon> pokemonList;
    private DatabaseController databaseController;

    public PokemonController(DatabaseController databaseController) {
        this.pokemonList = new ArrayList<>(); //Get all data from database to initialise new arraylist
        this.databaseController = databaseController;
    }

    public GridPane showPokemons(){
        //Create a gridpane to show all the pokemons
        GridPane grid = new GridPane();




        return grid;
    }

    public GridPane showPokemonDetail(){
        //Create a gridpane to show the details of a Pokemon
        GridPane grid = new GridPane();




        return grid;
    }

    public Pokemon getPokemon(int pokemonId){
        //Get Pokemon from database using the ID given
        return pokemonList.get(pokemonId);
    }

    public void addPokemon(Pokemon pokemon){
        //Add new Pokemon to database
        pokemonList.add(pokemon);
    }

    public void editPokemon (int pokemonId ,Pokemon pokemon){
        //Edit the Pokemon connected to the given ID with the data from the given Pokemon

    }

    public void deletePokemon (int pokemonId){
        //Delete the Pokemon connected to the given ID

    }

}
