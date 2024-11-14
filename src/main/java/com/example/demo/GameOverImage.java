package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The GameOverImage class represents the game over screen image in the game.
 * It extends the ImageView class and displays the "Game Over" image at a specified position.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png";

	/**
	 * Constructs a GameOverImage with the given x and y position.
	 * The image is loaded from the specified path and positioned on the screen.
	 *
	 * @param xPosition The x-coordinate for the position of the game over image.
	 * @param yPosition The y-coordinate for the position of the game over image.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
