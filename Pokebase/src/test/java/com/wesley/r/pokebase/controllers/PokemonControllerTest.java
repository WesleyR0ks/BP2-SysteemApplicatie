package com.wesley.r.pokebase.controllers;

import com.wesley.r.pokebase.classes.Pokemon;
import com.wesley.r.pokebase.classes.Type;
import com.wesley.r.pokebase.classes.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonControllerTest {

    private DatabaseController mockDatabaseController;
    private Connection mockConnection;
    private Statement mockStatement;

    private PokemonController pokemonController;

    private ArrayList<Type> mockTypes;
    private ArrayList<Zone> mockZones;

    private Type electricType;
    private Type waterType;
    private Zone kantoZone;
    private Zone johtoZone;

    @BeforeEach
    void setUp() throws Exception {
        // Manually create mocks instead of using @Mock annotation + MockitoExtension
        mockDatabaseController = mock(DatabaseController.class);
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);

        electricType = new Type(1, "Electric");
        waterType = new Type(2, "Water");
        kantoZone = new Zone(1, "Kanto");
        johtoZone = new Zone(2, "Johto");

        mockTypes = new ArrayList<>();
        mockTypes.add(electricType);
        mockTypes.add(waterType);

        mockZones = new ArrayList<>();
        mockZones.add(kantoZone);
        mockZones.add(johtoZone);

        when(mockDatabaseController.getDbConnection()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockDatabaseController.getTypesDB()).thenReturn(mockTypes);
        when(mockDatabaseController.getZonesDB()).thenReturn(mockZones);

        pokemonController = new PokemonController(mockDatabaseController);
    }

    @Test
    void testGetTypeList() {
        List<Type> result = pokemonController.getTypeList();
        assertEquals(2, result.size());
        assertEquals("Electric", result.get(0).getTypeName());
    }

    @Test
    void testGetZoneList() {
        List<Zone> result = pokemonController.getZoneList();
        assertEquals(2, result.size());
        assertEquals("Kanto", result.get(0).getZoneName());
    }

    @Test
    void testGetPokemon() {
        Pokemon mockPokemon = new Pokemon(1, "Pikachu", 35, 55, 40, 90, electricType, kantoZone);
        when(mockDatabaseController.getPokemonDB(1, mockTypes, mockZones)).thenReturn(mockPokemon);

        Pokemon result = pokemonController.getPokemon(1);
        assertNotNull(result);
        assertEquals("Pikachu", result.getPokemonName());
        assertEquals("Electric", result.getType().getTypeName());
    }

    @Test
    void testAddPokemon() {
        Pokemon newPokemon = new Pokemon("Charmander", 39, 52, 43, 65, new Type(3, "Fire"), kantoZone);
        doNothing().when(mockDatabaseController).addPokemonDB(newPokemon);

        pokemonController.addPokemon(newPokemon);

        verify(mockDatabaseController, times(1)).addPokemonDB(newPokemon);
    }

    @Test
    void testEditPokemon() {
        Pokemon editedPokemon = new Pokemon("Bulbasaur", 45, 49, 49, 45, new Type(4, "Grass"), johtoZone);
        doNothing().when(mockDatabaseController).updatePokemonDB(5, editedPokemon);

        pokemonController.editPokemon(5, editedPokemon);

        verify(mockDatabaseController).updatePokemonDB(5, editedPokemon);
    }

    @Test
    void testDeletePokemon() {
        doNothing().when(mockDatabaseController).deletePokemonDB(3);

        pokemonController.deletePokemon(3);

        verify(mockDatabaseController).deletePokemonDB(3);
    }
}
