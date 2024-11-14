package com.example.demo;

import java.util.*;

/**
 * The Boss class represents a powerful boss enemy in the game, extending the FighterPlane class.
 * It has unique attributes such as a move pattern, shield mechanics, and the ability to fire projectiles.
 */
public class Boss extends FighterPlane {

	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = .002;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 100;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 500;

	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;

	/**
	 * Constructs a Boss object, initializing its move pattern, shield state, and other properties.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Updates the position of the Boss by moving it vertically according to the move pattern.
	 * The Boss's vertical position is constrained to stay within the predefined bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the Boss's position and checks if its shield needs to be activated or deactivated.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the Boss's fire rate condition is met.
	 *
	 * @return A new BossProjectile if the Boss fires, or null if it does not.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Applies damage to the Boss. If the Boss is shielded, no damage is taken.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Initializes the move pattern by adding alternating vertical velocities and then shuffling the pattern.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield state. If the shield is active, its frames are counted.
	 * If the shield should be activated or deactivated, the respective action is taken.
	 */
	private void updateShield() {
		if (isShielded) framesWithShieldActivated++;
		else if (shieldShouldBeActivated()) activateShield();
		if (shieldExhausted()) deactivateShield();
	}

	/**
	 * Returns the next vertical movement in the Boss's move pattern, shuffling the pattern when needed.
	 *
	 * @return The next vertical movement value.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			Collections.shuffle(movePattern);
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
		}
		if (indexOfCurrentMove == movePattern.size()) {
			indexOfCurrentMove = 0;
		}
		return currentMove;
	}

	/**
	 * Determines if the Boss should fire a projectile in the current frame.
	 *
	 * @return True if the Boss fires a projectile, otherwise false.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Returns the initial y-coordinate position for firing a projectile.
	 *
	 * @return The initial position for the projectile's y-coordinate.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines if the shield should be activated based on a random probability.
	 *
	 * @return True if the shield should be activated, otherwise false.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines if the shield's active state has expired.
	 *
	 * @return True if the shield's active state is exhausted, otherwise false.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the Boss's shield.
	 */
	private void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the Boss's shield and resets the frames with shield activated.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}
}
