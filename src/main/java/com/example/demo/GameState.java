package com.example.demo;

import java.io.Serializable;

/**
 * GameState is a singleton that holds the current state of the game (e.g., health, score, level).
 */
public class GameState implements Serializable {

    private static GameState instance;
    private int playerHealth;
    private int score;
    private int currentLevel;
    private double playerX;
    private double playerY;

    private GameState() {
        // Initialize with default values
        playerHealth = 5;
        score = 0;
        currentLevel = 1;
    }

    public GameState(int playerHealth, double playerX, double playerY, int score, int currentLevel) {
        this.playerHealth = playerHealth;
        this.playerX = playerX;
        this.playerY = playerY;
        this.score = score;
        this.currentLevel = currentLevel;
    }

    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    // Getters and setters for player health, score, level, etc.
    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
