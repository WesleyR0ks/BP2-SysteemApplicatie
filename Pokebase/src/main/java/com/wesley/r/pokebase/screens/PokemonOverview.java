package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.controllers.DatabaseController;
import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PokemonOverview extends Basescreen {
    public PokemonOverview(Stage stage, PokemonController pokemonController) {
        super(stage, pokemonController);

        GridPane grid = pokemonController.showPokemons(stage);
        Button btnAdd = new Button("Add");
        grid.add(btnAdd, 0, 0);
        btnAdd.setOnAction(e -> {
            PokemonAdd pokemonAdd = new PokemonAdd(new Stage(), pokemonController);
            stage.close();
        });

        Scene scene = new Scene(grid, 900, 700);
        stage.setTitle("Pokebase Overview");
        stage.setScene(scene);
        stage.show();
    }
}



