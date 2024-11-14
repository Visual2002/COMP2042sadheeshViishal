package com.example.demo;

/**
 * The EnemyProjectile class represents a projectile fired by an enemy plane.
 * It extends the Projectile class and defines the behavior for the movement
 * and characteristics of the enemy's projectile.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructs an EnemyProjectile with the given initial x and y positions.
	 *
	 * @param initialXPos The initial x-coordinate position of the projectile.
	 * @param initialYPos The initial y-coordinate position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}

	/**
	 * Updates the position of the EnemyProjectile by moving it horizontally.
	 * The horizontal velocity is negative, meaning the projectile moves to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the EnemyProjectile by updating its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
