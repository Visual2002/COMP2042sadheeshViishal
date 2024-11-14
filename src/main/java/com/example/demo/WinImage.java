package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {

	// Constants for the image file, size, and visibility settings
	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png";
	private static final int HEIGHT = 500;
	private static final int WIDTH = 600;

	/**
	 * Constructs a new WinImage with the specified position.
	 * The image is initially hidden and positioned at the given coordinates.
	 *
	 * @param xPosition The X position of the image on the screen.
	 * @param yPosition The Y position of the image on the screen.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false);  // Initially set to hidden
		this.setFitHeight(HEIGHT);
		this.setFitWidth(WIDTH);
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Makes the win image visible on the screen.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}

}
