package com.example.demo;

public class UserPlane extends FighterPlane {

	// Constants for the user plane's image, position, and movement boundaries
	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;

	// Instance variables for velocity multiplier and number of kills
	private int velocityMultiplier;
	private int numberOfKills;

	/**
	 * Constructs a new UserPlane with the specified initial health.
	 * The UserPlane starts at a fixed initial position and can move vertically.
	 *
	 * @param initialHealth The initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		velocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane based on its current velocity.
	 * The plane moves up or down within defined vertical boundaries.
	 */
	@Override
	public void updatePosition() {
		if (isMoving()) {
			// Store the initial translateY position to restore it if the plane moves out of bounds
			double initialTranslateY = getTranslateY();
			// Move the plane vertically
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			// Check if the plane is within the defined vertical bounds, otherwise restore position
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	/**
	 * Updates the user plane's actor (including position).
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user plane at a specified position.
	 *
	 * @return A new UserProjectile object fired by the plane.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the plane is currently moving (either up or down).
	 *
	 * @return True if the plane is moving, false otherwise.
	 */
	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	/**
	 * Moves the plane up by setting the velocity multiplier to -1.
	 */
	public void moveUp() {
		velocityMultiplier = -1;
	}

	/**
	 * Moves the plane down by setting the velocity multiplier to 1.
	 */
	public void moveDown() {
		velocityMultiplier = 1;
	}

	/**
	 * Stops the plane from moving by setting the velocity multiplier to 0.
	 */
	public void stop() {
		velocityMultiplier = 0;
	}

	/**
	 * Gets the current number of kills made by the user plane.
	 *
	 * @return The number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the kill count by one.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

}
