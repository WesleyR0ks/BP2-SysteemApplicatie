package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.controllers.DatabaseController;
import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Basescreen {

    private Stage stage;
    private PokemonController pokemonController;

    public Basescreen(Stage stage, PokemonController pokemonController) {
        this.stage = stage;
        this.pokemonController = pokemonController;
    }

    public Stage getStage() {
        return stage;
    }

    public PokemonController getPokemonController() {
        return pokemonController;
    }
}
