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
    private int width;
    private int height;



    public Basescreen(Stage stage, PokemonController pokemonController) {
        this.stage = stage;
        this.pokemonController = pokemonController;
        this.width = 900;
        this.height = 700;
    }

    public Stage getStage() {
        return stage;
    }

    public PokemonController getPokemonController() {
        return pokemonController;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
