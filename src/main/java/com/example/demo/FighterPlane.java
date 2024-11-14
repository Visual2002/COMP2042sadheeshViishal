package com.example.demo;

/**
 * The FighterPlane class is an abstract class that represents a fighter plane in the game.
 * It extends the ActiveActorDestructible class and provides functionality for handling health,
 * taking damage, and firing projectiles. Specific types of fighter planes (like EnemyPlane and Boss)
 * will extend this class and implement the abstract methods.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	private int health;

	/**
	 * Constructs a FighterPlane with the given image name, image height, initial position, and health.
	 * This constructor initializes the fighter plane's health and passes the other parameters to the
	 * superclass constructor.
	 *
	 * @param imageName The image name for the fighter plane.
	 * @param imageHeight The height of the fighter plane's image.
	 * @param initialXPos The initial x-coordinate position of the fighter plane.
	 * @param initialYPos The initial y-coordinate position of the fighter plane.
	 * @param health The initial health of the fighter plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Abstract method that must be implemented by subclasses to define how the fighter plane fires its projectile.
	 *
	 * @return The projectile fired by the fighter plane.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Decreases the fighter plane's health by 1 when it takes damage. If the health reaches zero,
	 * the fighter plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the x-coordinate for firing a projectile based on the fighter plane's current position
	 * and the provided offset.
	 *
	 * @param xPositionOffset The x-position offset to adjust where the projectile should be fired from.
	 * @return The calculated x-coordinate for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the y-coordinate for firing a projectile based on the fighter plane's current position
	 * and the provided offset.
	 *
	 * @param yPositionOffset The y-position offset to adjust where the projectile should be fired from.
	 * @return The calculated y-coordinate for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the fighter plane's health has reached zero.
	 *
	 * @return true if the fighter plane's health is zero, otherwise false.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Returns the current health of the fighter plane.
	 *
	 * @return The health of the fighter plane.
	 */
	public int getHealth() {
		return health;
	}
}
