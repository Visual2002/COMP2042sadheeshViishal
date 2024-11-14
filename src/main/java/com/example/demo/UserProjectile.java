package com.example.demo;

public class UserProjectile extends Projectile {

	// Constants for the projectile's image, size, and horizontal velocity
	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructs a new UserProjectile at the specified initial position.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 * The projectile's horizontal velocity determines how far it moves each time.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the projectile's actor (i.e., its position).
	 * This method calls the updatePosition() method to move the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

}
