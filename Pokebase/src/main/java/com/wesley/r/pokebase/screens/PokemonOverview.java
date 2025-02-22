package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.controllers.DatabaseController;
import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PokemonOverview extends Basescreen {
    public PokemonOverview(Stage stage, PokemonController pokemonController) {
        super(stage, pokemonController);

        GridPane grid = pokemonController.showPokemons();

        Scene scene = new Scene(grid);
        stage.setTitle("Pokebase Overview");
        stage.setScene(scene);
        stage.show();
    }
}



