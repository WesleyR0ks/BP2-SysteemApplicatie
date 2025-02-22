package com.wesley.r.pokebase.controllers;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;
import com.wesley.r.pokebase.screens.PokemonDetail;
import com.wesley.r.pokebase.screens.PokemonOverview;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    public GridPane showPokemons(Stage stage){
        //Create a gridpane to show all the pokemons
        GridPane grid = new GridPane();
        grid.setHgap(15);
        try {
            ResultSet rs = statement.executeQuery("SELECT pokemon.pokemonID, pokemon.pokemonName, type.typeName ,zone.zoneName FROM pokemon LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID;");
            int i2 = 1;
            int i1 = 0;


            i2++;
            grid.add(new Label("Pokemon Name:"), i1, i2);
            i1++;
            grid.add(new Label("Type:"), i1, i2);
            i1++;
            grid.add(new Label("Zone:"), i1, i2);
            i2++;
            while (rs.next()) {
                i1 = 0;

                grid.add(new Label(rs.getString("pokemonName")), i1, i2);
                i1++;
                grid.add(new Label(rs.getString("typeName")), i1, i2);
                i1++;
                grid.add(new Label(rs.getString("zoneName")), i1, i2);
                i1++;
                Button btnSelect = new Button("Select");
                grid.add(btnSelect, i1, i2);
                int pokemonId = rs.getInt("pokemonID");
                btnSelect.setOnAction(e -> {
                    System.out.println(pokemonId);
                    PokemonDetail pokemonDetail = new PokemonDetail(new Stage(), this, pokemonId);
                    stage.close();
                });

                i2++;
            }

        } catch (SQLException e) {
            System.out.println("Couldn't execute showPokemons statement");
            throw new RuntimeException(e);
        }


        return grid;
    }

    public GridPane showPokemonDetail(int pokemonId, Stage stage){
        //Create a gridpane to show the details of a Pokemon
//        SELECT pokemon.pokemonID, pokemon.pokemonName, pokemon.healthValue, pokemon.attackValue, pokemon.defenseValue, pokemon.speedValue, type.typeName, zone.zoneName FROM `pokemon`
//        LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID
//        WHERE pokemonID = 1;

        GridPane grid = new GridPane();
        grid.setHgap(15);
        Button btnReturn = new Button("Return");
        grid.add(btnReturn, 0, 0);
        btnReturn.setOnAction(e -> {
            PokemonOverview pokemonOverview = new PokemonOverview(new Stage(), this);
            stage.close();
        });

        try {
            ResultSet rs = statement.executeQuery("SELECT pokemon.pokemonID, pokemon.pokemonName, pokemon.healthValue, pokemon.attackValue, pokemon.defenseValue, pokemon.speedValue, type.typeName, zone.zoneName FROM `pokemon`\n" +
                    "LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID\n" +
                    "WHERE pokemonID = '" + pokemonId + "';");
            int i2 = 1;
            int i1 = 0;
            grid.add(new Label("Pokemon Name:"), i1, i2);
            i1++;
            grid.add(new Label("Health Value:"), i1, i2);
            i1++;
            grid.add(new Label("Attack Value:"), i1, i2);
            i1++;
            grid.add(new Label("Defense Value:"), i1, i2);
            i1++;
            grid.add(new Label("Speed Value:"), i1, i2);
            i1++;
            grid.add(new Label("Type:"), i1, i2);
            i1++;
            grid.add(new Label("Zone:"), i1, i2);
            i2++;
            while (rs.next()) {
                i1 = 0;
                grid.add(new Label(rs.getString("pokemonName")), i1, i2);
                i1++;
                grid.add(new Label(rs.getString("healthValue")), i1, i2);
                i1++;
                grid.add(new Label(rs.getString("attackValue")),i1, i2);
                i1++;
                grid.add(new Label(rs.getString("defenseValue")),i1, i2);
                i1++;
                grid.add(new Label(rs.getString("speedValue")),i1, i2);
                i1++;
                grid.add(new Label(rs.getString("typeName")), i1, i2);
                i1++;
                grid.add(new Label(rs.getString("zoneName")), i1, i2);
                i1++;
                Button btnEdit = new Button("Edit");
                grid.add(btnEdit, i1, i2);
                btnEdit.setOnAction(e -> {

                });
                i1++;
                Button btnDelete = new Button("Delete");
                grid.add(btnDelete, i1, i2);
                btnDelete.setOnAction(e -> {

                });

                i2++;
            }

        } catch (SQLException e) {
            System.out.println("Couldn't execute statement");
            throw new RuntimeException(e);
        }





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
