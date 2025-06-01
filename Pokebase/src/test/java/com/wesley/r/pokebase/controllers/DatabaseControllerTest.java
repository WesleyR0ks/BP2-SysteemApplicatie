package com.wesley.r.pokebase.controllers;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;
import com.wesley.r.pokebase.controllers.DatabaseController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseControllerTest {

    private DatabaseController databaseController;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        databaseController = new DatabaseController();

        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        databaseController.setDbConnection(mockConnection);
        databaseController.setStatement(mockStatement);
    }

    @Test
    void getDbConnection() {
        assertEquals(mockConnection, databaseController.getDbConnection());
    }

    @Test
    void getTypesDB() throws SQLException {
        when(mockStatement.executeQuery("SELECT * FROM type;")).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("typeID")).thenReturn(1, 2);
        when(mockResultSet.getString("typeName")).thenReturn("Electric", "Water");

        ArrayList<Type> types = databaseController.getTypesDB();

        assertEquals(2, types.size());
        assertEquals(1, types.get(0).getTypeId());
        assertEquals("Electric", types.get(0).getTypeName());
        assertEquals(2, types.get(1).getTypeId());
        assertEquals("Water", types.get(1).getTypeName());
    }

    @Test
    void getZonesDB() throws SQLException {
        when(mockStatement.executeQuery("SELECT * FROM zone;")).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("zoneID")).thenReturn(10);
        when(mockResultSet.getString("zoneName")).thenReturn("Forest");

        ArrayList<Zone> zones = databaseController.getZonesDB();

        assertEquals(1, zones.size());
        assertEquals(10, zones.get(0).getZoneId());
        assertEquals("Forest", zones.get(0).getZoneName());
    }

    @Test
    void getPokemonsResultSet() throws SQLException {
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        ResultSet rs = databaseController.getPokemonsResultSet();

        assertNotNull(rs);
        verify(mockStatement).executeQuery(
                "SELECT pokemon.pokemonID, pokemon.pokemonName, type.typeName ,zone.zoneName FROM pokemon LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID;"
        );
    }

    @Test
    void getPokemonResultSet() throws SQLException {
        int pokemonId = 5;
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        ResultSet rs = databaseController.getPokemonResultSet(pokemonId);

        assertNotNull(rs);
        verify(mockStatement).executeQuery(
                "SELECT pokemon.pokemonID, pokemon.pokemonName, pokemon.healthValue, pokemon.attackValue, pokemon.defenseValue, pokemon.speedValue, pokemon.typeID, type.typeName, pokemon.zoneID, zone.zoneName FROM `pokemon`\n" +
                        "LEFT JOIN type ON pokemon.typeID = type.typeID LEFT JOIN zone ON pokemon.zoneID = zone.zoneID\n" +
                        "WHERE pokemonID = '" + pokemonId + "';"
        );
    }

    @Test
    void getPokemonDB() throws SQLException {
        int pokemonId = 1;

        Type electric = new Type(1, "Electric");
        Zone forest = new Zone(1, "Forest");
        ArrayList<Type> typeList = new ArrayList<>();
        typeList.add(electric);
        ArrayList<Zone> zoneList = new ArrayList<>();
        zoneList.add(forest);

        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simulate result set data for one Pokemon
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("pokemonID")).thenReturn(pokemonId);
        when(mockResultSet.getString("pokemonName")).thenReturn("Pikachu");
        when(mockResultSet.getInt("healthValue")).thenReturn(35);
        when(mockResultSet.getInt("attackValue")).thenReturn(55);
        when(mockResultSet.getInt("defenseValue")).thenReturn(40);
        when(mockResultSet.getInt("speedValue")).thenReturn(90);
        when(mockResultSet.getString("typeName")).thenReturn("Electric");
        when(mockResultSet.getString("zoneName")).thenReturn("Forest");

        Pokemon pokemon = databaseController.getPokemonDB(pokemonId, typeList, zoneList);

        assertNotNull(pokemon);
        assertEquals(pokemonId, pokemon.getPokemonId());
        assertEquals("Pikachu", pokemon.getPokemonName());
        assertEquals(35, pokemon.getHealthValue());
        assertEquals(electric, pokemon.getType());
        assertEquals(forest, pokemon.getZone());
    }

    @Test
    void addPokemonDB() throws SQLException {
        Pokemon pokemon = mock(Pokemon.class);
        when(pokemon.getPokemonName()).thenReturn("Bulbasaur");
        when(pokemon.getHealthValue()).thenReturn(45);
        when(pokemon.getAttackValue()).thenReturn(49);
        when(pokemon.getDefenseValue()).thenReturn(49);
        when(pokemon.getSpeedValue()).thenReturn(45);

        Type type = mock(Type.class);
        when(type.getTypeName()).thenReturn("Grass");
        when(pokemon.getType()).thenReturn(type);

        Zone zone = mock(Zone.class);
        when(zone.getZoneName()).thenReturn("Forest");
        when(pokemon.getZone()).thenReturn(zone);

        databaseController.addPokemonDB(pokemon);

        String expectedSql = "INSERT INTO `pokemon`(`pokemonName`, `healthValue`, `attackValue`, `defenseValue`, `speedValue`, `typeID`, `zoneID`) " +
                "VALUES ('Bulbasaur','45','49','49','45',(SELECT typeID FROM type WHERE typeName = 'Grass')," +
                "(SELECT zoneID FROM zone WHERE zoneName = 'Forest'));";

        verify(mockStatement).execute(expectedSql);
    }

    @Test
    void updatePokemonDB() throws SQLException {
        int pokemonId = 2;
        Pokemon pokemon = mock(Pokemon.class);
        when(pokemon.getPokemonName()).thenReturn("Charmander");
        when(pokemon.getHealthValue()).thenReturn(39);
        when(pokemon.getAttackValue()).thenReturn(52);
        when(pokemon.getDefenseValue()).thenReturn(43);
        when(pokemon.getSpeedValue()).thenReturn(65);

        databaseController.updatePokemonDB(pokemonId, pokemon);

        String expectedSql = "UPDATE `pokemon` SET `pokemonName`='Charmander',`healthValue`='39',`attackValue`='52',`defenseValue`='43',`speedValue`='65' WHERE `pokemonID` = '" + pokemonId + "';";
        verify(mockStatement).execute(expectedSql);
    }

    @Test
    void deletePokemonDB() throws SQLException {
        int pokemonId = 3;

        databaseController.deletePokemonDB(pokemonId);

        String expectedSql = "DELETE FROM `pokemon` WHERE `pokemonID` = '" + pokemonId + "';";
        verify(mockStatement).execute(expectedSql);
    }
}
