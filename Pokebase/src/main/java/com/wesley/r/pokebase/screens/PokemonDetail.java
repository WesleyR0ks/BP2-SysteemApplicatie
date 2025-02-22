package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PokemonDetail extends Basescreen {

    private int pokemonID;

    public PokemonDetail(Stage stage, PokemonController pokemonController, int pokemonID) {
        super(stage, pokemonController);

        this.pokemonID = pokemonID;

        GridPane grid = pokemonController.showPokemonDetail(pokemonID, stage);


        Scene scene = new Scene(grid, getWidth(), getHeight());
        stage.setTitle("Pokebase Overview");
        stage.setScene(scene);
        stage.show();


    }
}
