package com.example.demo;

import javafx.scene.image.*;

/**
 * The ActiveActor class is an abstract base class representing an active game
 * object with a position and image. It provides methods to manage and update
 * the actor's position, and subclasses must implement specific behavior by
 * defining the updatePosition method.
 */
public abstract class ActiveActor extends ImageView {

	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs an ActiveActor with the specified image, position, and height.
	 *
	 * @param imageName The filename of the image to display for this actor.
	 * @param imageHeight The height to display the image for this actor.
	 * @param initialXPos The initial x-coordinate position of this actor.
	 * @param initialYPos The initial y-coordinate position of this actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of this actor. This method must be implemented by subclasses
	 * to specify the actor's behavior on each update (e.g., moving or changing position).
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove The distance to move the actor along the x-axis.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove The distance to move the actor along the y-axis.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
