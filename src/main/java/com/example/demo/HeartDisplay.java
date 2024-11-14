package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The HeartDisplay class is responsible for displaying and managing heart icons on the screen.
 * It uses an HBox to arrange heart images horizontally and provides functionality to remove hearts
 * as the player loses health.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a HeartDisplay object with a specified position and number of hearts to display.
	 *
	 * @param xPosition The x-coordinate for the position of the heart container.
	 * @param yPosition The y-coordinate for the position of the heart container.
	 * @param heartsToDisplay The initial number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container (HBox) for holding the heart images.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes and adds heart images to the container based on the number of hearts to display.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the display. This is typically called when the player takes damage.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Returns the HBox container holding the heart images.
	 *
	 * @return The HBox containing the heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}
