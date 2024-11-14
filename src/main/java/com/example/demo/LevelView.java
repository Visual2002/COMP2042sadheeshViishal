package com.example.demo;

import javafx.scene.Group;

public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = -160;
	private static final int LOSS_SCREEN_Y_POSISITION = -375;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	/**
	 * Constructs the LevelView object, initializing all the components needed for displaying
	 * the game level's UI elements like the heart display and win/loss images.
	 *
	 * @param root The root Group that contains all UI elements of the level.
	 * @param heartsToDisplay The number of hearts to initially display based on player's health.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
	}

	/**
	 * Adds the heart display (showing the player's health) to the game scene.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen. This image appears when the player wins the level.
	 */
	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over image when the player loses the game.
	 */
	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Updates the heart display by removing hearts based on the player's remaining health.
	 *
	 * @param heartsRemaining The number of hearts the player has left, indicating their current health.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		// Remove hearts based on the difference between the current hearts and remaining hearts
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
