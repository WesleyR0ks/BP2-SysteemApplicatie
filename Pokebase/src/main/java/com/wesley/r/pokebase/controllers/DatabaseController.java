package com.wesley.r.pokebase.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseController {

    private String connectionCode;
    private Connection dbConnection;

    public DatabaseController() {
        this.connectionCode = "jdbc:mysql://localhost:3306/pokedexbase?user=root&password=";
        try {
            this.dbConnection = DriverManager.getConnection(connectionCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
