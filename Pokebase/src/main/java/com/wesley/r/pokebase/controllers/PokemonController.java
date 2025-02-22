package com.wesley.r.pokebase.controllers;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;
import javafx.scene.layout.GridPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PokemonController {

    private ArrayList<Pokemon> pokemonList;
    private ArrayList<Type> typeList;
    private ArrayList<Zone> zoneList;
    private DatabaseController databaseController;
    private Statement statement;

    public PokemonController(DatabaseController databaseController) {
        this.pokemonList = new ArrayList<>(); //Get all data from database to initialise new arraylist
        this.databaseController = databaseController;

        try {
            this.statement = databaseController.getDbConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public GridPane showPokemons(){
        //Create a gridpane to show all the pokemons
        GridPane grid = new GridPane();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM pokemon");
        } catch (SQLException e) {
            System.out.println("Couldn't execute statement");
            throw new RuntimeException(e);
        }


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
        //        INSERT INTO `pokemon`(`pokemonName`, `healthValue`, `attackValue`, `defenseValue`, `speedValue`, `typeID`, `zoneID`) VALUES ('Bulbasaur','45','49','49','45',
        //        (SELECT typeID FROM type WHERE typeName = 'Grass'),
        //        (SELECT zoneID FROM zone WHERE zoneName = 'Forest'));


        String pokemonName = pokemon.getPokemonName();
        int healthValue = pokemon.getHealthValue();
        int attackValue = pokemon.getAttackValue();
        int defenseValue = pokemon.getDefenseValue();
        int speedValue = pokemon.getSpeedValue();
        Type type = pokemon.getType();
        Zone zone = pokemon.getZone();

        try {
            statement.execute("INSERT INTO `pokemon`(`pokemonName`, `healthValue`, `attackValue`, `defenseValue`, `speedValue`, `typeID`, `zoneID`) " +
                    "VALUES ('"+ pokemonName +"'," +
                    "'" + healthValue + "'," +
                    "'" + attackValue + "'," +
                    "'" + defenseValue + "'," +
                    "'" + speedValue + "'," +
                    "(SELECT typeID FROM type WHERE typeName = '"+ type.getTypeName() +"')," +
                    "(SELECT zoneID FROM zone WHERE zoneName = '" + zone.getZoneName() +"'));");
        } catch (SQLException e) {
            System.out.println("Couldn't execute insert statement");
            throw new RuntimeException(e);
        }


        pokemonList.add(pokemon);
    }

    public void editPokemon (int pokemonId ,Pokemon pokemon){
        //Edit the Pokemon connected to the given ID with the data from the given Pokemon

    }

    public void deletePokemon (int pokemonId){
        //Delete the Pokemon connected to the given ID

    }

}
