package com.wesley.r.pokebase.classes;

public class Pokemon {

    private int pokemonId;
    private String pokemonName;
    private int healthValue;
    private int attackValue;
    private int defenseValue;
    private int speedValue;
    private Type type;
    private Zone zone;

    public Pokemon(int pokemonId, String pokemonName, int healthValue, int attackValue, int defenseValue, int speedValue, Type type, Zone zone) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.healthValue = healthValue;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
        this.speedValue = speedValue;
        this.type = type;
        this.zone = zone;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(int healthValue) {
        this.healthValue = healthValue;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }

    public int getSpeedValue() {
        return speedValue;
    }

    public void setSpeedValue(int speedValue) {
        this.speedValue = speedValue;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
