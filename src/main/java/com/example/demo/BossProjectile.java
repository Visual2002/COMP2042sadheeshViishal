package com.example.demo;

/**
 * The BossProjectile class represents a projectile fired by the Boss in the game.
 * It extends the Projectile class and defines the behavior for the movement and
 * characteristics of the Boss's projectile.
 */
public class BossProjectile extends Projectile {

	private static final String IMAGE_NAME = "fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructs a BossProjectile with the given initial y-position.
	 *
	 * @param initialYPos The initial y-coordinate position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
	}

	/**
	 * Updates the position of the BossProjectile, moving it horizontally.
	 * The horizontal movement is fixed with a negative velocity, making it move to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the BossProjectile by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
