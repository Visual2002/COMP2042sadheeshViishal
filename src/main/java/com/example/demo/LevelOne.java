package com.example.demo;

public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final int KILLS_TO_ADVANCE = 10;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * Constructs the LevelOne object with the specified screen dimensions.
	 * Initializes the background and player health for the level.
	 *
	 * @param screenHeight The height of the screen.
	 * @param screenWidth The width of the screen.
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over based on the player's status or kill target.
	 * If the player is destroyed, the game ends, or if the player reaches the
	 * required number of kills, the game progresses to the next level.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame(); // Player has lost the game
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL); // Player has reached the kill target and advances to the next level
		}
	}

	/**
	 * Initializes the friendly units in the level by adding the player's unit to the game root.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser()); // Adds the player to the scene
	}

	/**
	 * Spawns enemy units at random intervals and positions on the screen.
	 * The number of enemies spawned is controlled by the total enemies and spawn probability.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy); // Adds the new enemy to the level
			}
		}
	}

	/**
	 * Instantiates and returns the LevelView for LevelOne, which includes the game root and player health display.
	 *
	 * @return The LevelView instance for LevelOne.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the player has reached the required number of kills to advance to the next level.
	 *
	 * @return True if the player has reached the kill target, otherwise false.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE; // Player's kills have reached the target
	}

}
