package com.example.demo;

/**
 * The EnemyPlane class represents an enemy plane in the game that moves horizontally
 * and can fire projectiles. It extends the FighterPlane class and defines the behavior
 * for the movement, projectile firing, and health of the enemy plane.
 */
public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	/**
	 * Constructs an EnemyPlane at the given initial position.
	 *
	 * @param initialXPos The initial x-coordinate position of the enemy plane.
	 * @param initialYPos The initial y-coordinate position of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
	}

	/**
	 * Updates the position of the EnemyPlane, moving it horizontally.
	 * The horizontal velocity is negative, meaning the plane moves to the left.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Fires a projectile if the random condition based on the fire rate is met.
	 * The position of the projectile is offset from the plane's position.
	 *
	 * @return A new EnemyProjectile if the fire rate condition is met, otherwise null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	/**
	 * Updates the state of the EnemyPlane by updating its position and potentially firing projectiles.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
