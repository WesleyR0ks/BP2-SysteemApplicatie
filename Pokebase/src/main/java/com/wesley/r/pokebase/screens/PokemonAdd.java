package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PokemonAdd extends Basescreen{
    public PokemonAdd(Stage stage, PokemonController pokemonController) {
        super(stage, pokemonController);

        GridPane grid = new GridPane();
        grid.setHgap(15);
        Button btnReturn = new Button("Return");
        grid.add(btnReturn, 0, 0);
        btnReturn.setOnAction(e -> {
            PokemonOverview pokemonOverview = new PokemonOverview(new Stage(), pokemonController);
            stage.close();
        });



        Scene scene = new Scene(grid, getWidth(), getHeight());
        stage.setTitle("Add Pokemon");
        stage.setScene(scene);
        stage.show();
    }
}
