package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {

	// Path to the shield image file.
	private static final String IMAGE_NAME = "/images/shield.png";

	// Size of the shield image (both width and height).
	private static final int SHIELD_SIZE = 200;

	/**
	 * Constructs a new ShieldImage with the specified initial position.
	 * The shield image is initially hidden and set to the specified size.
	 *
	 * @param xPosition The initial x-position of the shield on the screen.
	 * @param yPosition The initial y-position of the shield on the screen.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		// Set the shield image
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.jpg").toExternalForm()));
		// Initially hide the shield
		this.setVisible(false);
		// Set the size of the shield
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Makes the shield visible on the screen.
	 * This method is called when the shield needs to appear (e.g., when activated).
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield from the screen.
	 * This method is called when the shield needs to be deactivated or disappear.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}
