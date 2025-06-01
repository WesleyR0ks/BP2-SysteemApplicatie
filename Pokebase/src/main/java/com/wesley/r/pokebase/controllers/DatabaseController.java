package com.wesley.r.pokebase.controllers;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {

    private String connectionCode;
    private Connection dbConnection;
    private Statement statement;

    public DatabaseController() {
        this.connectionCode = "jdbc:mysql://localhost:3306/pokedexbase?user=root&password=";
        try {
            this.dbConnection = DriverManager.getConnection(connectionCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.statement = getDbConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getDbConnection() {
        return dbConnection;
    }

    public ArrayList<Type> getTypesDB() {
        ArrayList<Type> types = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM type;");
            while (rs.next()) {
                types.add(new Type(rs.getInt("typeID"), rs.getString("typeName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return types;
    }

    public ArrayList<Zone> getZonesDB() {
        ArrayList<Zone> zones = new ArrayList<>();
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM zone;");
            while (rs.next()) {
                zones.add(new Zone(rs.getInt("zoneID"), rs.getString("zoneName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return zones;
    }

    public ResultSet getPokemonsResultSet() {
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT pokemon.pokemonID, pokemon.pokemonName, type.typeName ,zone.zoneName FROM pokemon LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }


    public ResultSet getPokemonResultSet(int pokemonId) {
        //Get Pokemon from database using the ID given
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT pokemon.pokemonID, pokemon.pokemonName, pokemon.healthValue, pokemon.attackValue, pokemon.defenseValue, pokemon.speedValue, pokemon.typeID, type.typeName, pokemon.zoneID, zone.zoneName FROM `pokemon`\n" +
                    "LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID\n" +
                    "WHERE pokemonID = '" + pokemonId + "';");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public Pokemon getPokemonDB(int pokemonId, ArrayList<Type> typeList, ArrayList<Zone> zoneList) {
        Pokemon pokemon = null;

        ResultSet rs = getPokemonResultSet(pokemonId);
        try {
            while (rs.next()) {
                Type foundType = null;
                for (Type type : typeList){
                    if (type.getTypeName().equals(rs.getString("typeName"))) {
                        foundType = type;
                    }
                }

                Zone foundZone = null;
                for (Zone zone : zoneList){
                    if (zone.getZoneName().equals(rs.getString("zoneName"))) {
                        foundZone = zone;
                    }
                }

                pokemon = new Pokemon(rs.getInt("pokemonID"),
                        rs.getString("pokemonName"),
                        rs.getInt("healthValue"),
                        rs.getInt("attackValue"),
                        rs.getInt("defenseValue"),
                        rs.getInt("speedValue"),
                        foundType,
                        foundZone);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pokemon;
    }

    public void addPokemonDB(Pokemon pokemon) {
        try {
            statement.execute("INSERT INTO `pokemon`(`pokemonName`, `healthValue`, `attackValue`, `defenseValue`, `speedValue`, `typeID`, `zoneID`) " +
                    "VALUES ('"+ pokemon.getPokemonName() +"'," +
                    "'" + pokemon.getHealthValue() + "'," +
                    "'" + pokemon.getAttackValue() + "'," +
                    "'" + pokemon.getDefenseValue() + "'," +
                    "'" + pokemon.getSpeedValue() + "'," +
                    "(SELECT typeID FROM type WHERE typeName = '"+ pokemon.getType().getTypeName() +"')," +
                    "(SELECT zoneID FROM zone WHERE zoneName = '" + pokemon.getZone().getZoneName() +"'));");
        } catch (SQLException e) {
            System.out.println("Couldn't execute insert statement");
            throw new RuntimeException(e);
        }
    }

    public void updatePokemonDB(int pokemonId, Pokemon pokemon) {
        try {
            statement.execute("UPDATE `pokemon` " +
                    "SET `pokemonName`='"+ pokemon.getPokemonName() +"'," +
                    "`healthValue`='"+ pokemon.getHealthValue() +"'," +
                    "`attackValue`='"+ pokemon.getAttackValue() +"'," +
                    "`defenseValue`='"+ pokemon.getDefenseValue() +"'," +
                    "`speedValue`='"+ pokemon.getSpeedValue() +"'" +
                    "WHERE `pokemonID` = '"+ pokemonId +"';");
        } catch (SQLException e) {
            System.out.println("Couldn't execute update statement");
            throw new RuntimeException(e);
        }
    }

    public void deletePokemonDB(int pokemonId) {
        try {
            statement.execute("DELETE FROM `pokemon` WHERE `pokemonID` = '"+ pokemonId +"';");
        } catch (SQLException e) {
            System.out.println("Couldn't execute update statement");
            throw new RuntimeException(e);
        }
    }
}
