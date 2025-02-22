package com.wesley.r.pokebase;

import com.wesley.r.pokebase.controllers.DatabaseController;
import com.wesley.r.pokebase.controllers.PokemonController;
import com.wesley.r.pokebase.screens.PokemonOverview;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DatabaseController controller = new DatabaseController();
        PokemonController pokemonController = new PokemonController(controller);
        PokemonOverview overview = new PokemonOverview(stage, pokemonController);


    }

    public static void main(String[] args) {
        launch();
    }
}