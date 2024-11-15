package com.example.demo;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * GameController manages the game flow, including starting, pausing, and saving the game.
 */
public class GameController {

    private Stage stage;
    private boolean isPaused;
    private LevelParent currentLevel;
    private int playerHealth;
    private double playerX;
    private double playerY;
    private int score;


    /**
     * Constructor for initializing the GameController.
     *
     * @param stage The primary stage of the game.
     */
    public GameController(Stage stage) {
        this.stage = stage;
        this.isPaused = false;
    }

    /**
     * Starts the game by initializing the first level and setting up the scene.
     */
    public void startGame() {
        currentLevel = new LevelOne(600, 800);  // example level
        Scene scene = currentLevel.initializeScene();
        scene.setOnKeyPressed(this::handleKeyPress);
        stage.setScene(scene);
        stage.show();
        currentLevel.startGame();
    }

    /**
     * Handles the key press events, allowing pausing or saving.
     *
     * @param event The key event that occurs when a key is pressed.
     */
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.P) {
            togglePause();
        }
        if (event.getCode() == KeyCode.S) {
            saveGame();
        }
    }

    /**
     * Pauses or resumes the game based on the current state.
     */
    private void togglePause() {
        if (isPaused) {
            currentLevel.startGame();  // Resume the game
        } else {
            currentLevel.stopGame();  // Pause the game
        }
        isPaused = !isPaused;
    }

    /**
     * Saves the current game state to a file.
     */
    private void saveGame() {
        String fileName = "savegame.txt";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            // Save user plane's state
            writer.println("UserPlaneHealth:" + currentLevel.getUser().getHealth());
            writer.println("UserPlaneKills:" + currentLevel.getUser().getNumberOfKills());

            // Save level information
            writer.println("CurrentLevel:" + currentLevel.getClass().getSimpleName());

            // Save enemy information
            writer.println("EnemiesRemaining:" + currentLevel.getCurrentNumberOfEnemies());

            System.out.println("Game saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }
}
