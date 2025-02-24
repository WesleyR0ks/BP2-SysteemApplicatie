package com.wesley.r.pokebase.screens;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;
import com.wesley.r.pokebase.controllers.PokemonController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PokemonEdit extends Basescreen {
    public PokemonEdit(Stage stage, PokemonController pokemonController, int pokemonId) {
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

        Pokemon selectedPokemon = pokemonController.getPokemon(pokemonId);

        Label lbPokemonName = new Label("Pokemon Name:");
        TextField tfPokemonName = new TextField();
        tfPokemonName.setText(selectedPokemon.getPokemonName());
        Label lbHealthValue = new Label("Health Value:");
        TextField tfHealthValue = new TextField();

        tfHealthValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")){
                    tfHealthValue.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });

        tfHealthValue.setText(String.valueOf(selectedPokemon.getHealthValue()));
        Label lbAttackValue = new Label("Attack Value:");
        TextField tfAttackValue = new TextField();

        tfAttackValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")){
                    tfAttackValue.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });

        tfAttackValue.setText(String.valueOf(selectedPokemon.getAttackValue()));
        Label lbDefenseValue = new Label("Defense Value:");
        TextField tfDefenseValue = new TextField();

        tfDefenseValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")){
                    tfDefenseValue.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });

        tfDefenseValue.setText(String.valueOf(selectedPokemon.getDefenseValue()));
        Label lbSpeedValue = new Label("Speed Value:");
        TextField tfSpeedValue = new TextField();

        tfSpeedValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!t1.matches("\\d*")){
                    tfSpeedValue.setText(t1.replaceAll("[^\\d]", ""));
                }
            }
        });

        tfSpeedValue.setText(String.valueOf(selectedPokemon.getSpeedValue()));
//        Label lbType = new Label("Type:");
//        ComboBox cbType = new ComboBox();
//        for (Type type : pokemonController.getTypeList()){
//            cbType.getItems().add(type.getTypeName());
//        }
//        cbType.setValue(selectedPokemon.getType().getTypeName());
//
//        Label lbZone = new Label("Zone:");
//        ComboBox cbZone = new ComboBox();
//        for (Zone zone : pokemonController.getZoneList()){
//            cbZone.getItems().add(zone.getZoneName());
//        }
//        cbZone.setValue(selectedPokemon.getZone().getZoneName());

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
//        grid.add(lbType, 0, i2);
//        grid.add(cbType, 1, i2);
//        i2++;
//        grid.add(lbZone, 0, i2);
//        grid.add(cbZone, 1, i2);
//        i2++;

        Button btnSave = new Button("Save");
        grid.add(btnSave, 0, i2);
        i2++;

        btnSave.setOnAction(e -> {
            if (tfPokemonName.getText().isEmpty() || tfHealthValue.getText().isEmpty() || tfAttackValue.getText().isEmpty() || tfDefenseValue.getText().isEmpty() || tfSpeedValue.getText().isEmpty()){

                System.out.println("Some fields are empty");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Input error");
                alert.setContentText("Some fields are empty");
                alert.showAndWait();

            } else {

                Pokemon updatedPokemon = new Pokemon(tfPokemonName.getText(), Integer.parseInt(tfHealthValue.getText()), Integer.parseInt(tfAttackValue.getText()), Integer.parseInt(tfDefenseValue.getText()), Integer.parseInt(tfSpeedValue.getText()), selectedPokemon.getType(), selectedPokemon.getZone());
                pokemonController.editPokemon(pokemonId, updatedPokemon);
                PokemonOverview pokemonOverview = new PokemonOverview(new Stage(), pokemonController);
                stage.close();

            }
        });

        Scene scene = new Scene(grid, getWidth(), getHeight());
        stage.setTitle("Pokebase Edit Screen");
        stage.setScene(scene);
        stage.show();
    }
}
