package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;
import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PokemonAdd extends Basescreen{
    public PokemonAdd(Stage stage, PokemonController pokemonController) {
        super(stage, pokemonController);

        GridPane grid = new GridPane();
        grid.setHgap(15);

        int i2 = 0;

        Button btnReturn = new Button("Return");
        grid.add(btnReturn, 0, i2);
        i2++;
        btnReturn.setOnAction(e -> {
            PokemonOverview pokemonOverview = new PokemonOverview(new Stage(), pokemonController);
            stage.close();
        });

        Label lbPokemonName = new Label("Pokemon Name:");
        TextField tfPokemonName = new TextField();
        Label lbHealthValue = new Label("Health Value:");
        TextField tfHealthValue = new TextField();
        Label lbAttackValue = new Label("Attack Value:");
        TextField tfAttackValue = new TextField();
        Label lbDefenseValue = new Label("Defense Value:");
        TextField tfDefenseValue = new TextField();
        Label lbSpeedValue = new Label("Speed Value:");
        TextField tfSpeedValue = new TextField();
        Label lbType = new Label("Type:");
        ComboBox cbType = new ComboBox();
        for (Type type : pokemonController.getTypeList()){
            cbType.getItems().add(type.getTypeName());
            cbType.setValue(type.getTypeName());
        }

        Label lbZone = new Label("Zone:");
        ComboBox cbZone = new ComboBox();
        for (Zone zone : pokemonController.getZoneList()){
            cbZone.getItems().add(zone.getZoneName());
            cbZone.setValue(zone.getZoneName());
        }

        grid.add(lbPokemonName, 0, i2);
        grid.add(tfPokemonName, 1, i2);
        i2++;
        grid.add(lbHealthValue, 0, i2);
        grid.add(tfHealthValue, 1, i2);
        i2++;
        grid.add(lbAttackValue, 0, i2);
        grid.add(tfAttackValue, 1, i2);
        i2++;
        grid.add(lbDefenseValue, 0, i2);
        grid.add(tfDefenseValue, 1, i2);
        i2++;
        grid.add(lbSpeedValue, 0, i2);
        grid.add(tfSpeedValue, 1, i2);
        i2++;
        grid.add(lbType, 0, i2);
        grid.add(cbType, 1, i2);
        i2++;
        grid.add(lbZone, 0, i2);
        grid.add(cbZone, 1, i2);
        i2++;

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            Type selectedType = null;
            Zone selectedZone = null;
            for (Type type : pokemonController.getTypeList()){
                if (type.getTypeName().equals(cbType.getValue())) {
                    selectedType = type;
                }
            }
            for (Zone zone : pokemonController.getZoneList()){
                if (zone.getZoneName().equals(cbZone.getValue())) {
                    selectedZone = zone;
                }
            }

            Pokemon pokemon = new Pokemon(tfPokemonName.getText(), Integer.parseInt(tfHealthValue.getText()), Integer.parseInt(tfAttackValue.getText()), Integer.parseInt(tfDefenseValue.getText()), Integer.parseInt(tfSpeedValue.getText()), selectedType, selectedZone);
            pokemonController.addPokemon(pokemon);

            PokemonOverview pokemonOverview = new PokemonOverview(new Stage(), pokemonController);
            stage.close();
        });

        grid.add(btnAdd, 0, i2);






        Scene scene = new Scene(grid, getWidth(), getHeight());
        stage.setTitle("Add Pokemon");
        stage.setScene(scene);
        stage.show();
    }
}
