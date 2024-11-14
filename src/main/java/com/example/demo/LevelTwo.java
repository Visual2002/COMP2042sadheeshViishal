package com.example.demo;

public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelViewLevelTwo levelView;

	/**
	 * Constructs the LevelTwo object, initializing the level with a specific background image,
	 * screen dimensions, and the player's initial health.
	 *
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	/**
	 * Initializes the friendly units (player and associated entities) for Level Two.
	 * Adds the player plane (user) to the game scene.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over based on the current state of the player and boss.
	 * The game ends if the player is destroyed or the boss is defeated.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units for Level Two. In this level, only the boss is spawned once all other enemies are defeated.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
		}
	}

	/**
	 * Instantiates and returns the LevelView specific to Level Two.
	 * This view displays additional information for this level, such as the health of the player.
	 *
	 * @return The LevelView for Level Two.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
		return levelView;
	}

}
