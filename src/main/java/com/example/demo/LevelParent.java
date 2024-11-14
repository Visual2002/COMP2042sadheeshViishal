package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

public abstract class LevelParent extends Observable {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private LevelView levelView;

	/**
	 * Constructs the LevelParent object, initializing game parameters such as the screen dimensions,
	 * background image, and player health.
	 *
	 * @param backgroundImageName The name of the background image for the level.
	 * @param screenHeight The height of the game screen.
	 * @param screenWidth The width of the game screen.
	 * @param playerInitialHealth The initial health of the player.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
		friendlyUnits.add(user);
	}

	/**
	 * Abstract method to initialize the friendly units (e.g., the player plane).
	 * This method must be implemented by subclasses.
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Abstract method to check if the game is over based on the player's state or progress.
	 * This method must be implemented by subclasses.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method to spawn enemy units in the game. This method must be implemented by subclasses.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate and return the LevelView for the current level.
	 * This method must be implemented by subclasses.
	 *
	 * @return The LevelView instance for the level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes the game scene with background, friendly units, and the heart display.
	 *
	 * @return The initialized Scene object.
	 */
	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by playing the timeline animation.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	/**
	 * Notifies observers that the player has reached the end of the level and is moving on to the next level.
	 *
	 * @param levelName The name of the next level to transition to.
	 */
	public void goToNextLevel(String levelName) {
		setChanged();
		notifyObservers(levelName);
	}

	/**
	 * Updates the game state by spawning enemies, updating actors, handling collisions,
	 * and checking for game-over conditions.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes the game timeline, setting up a keyframe to update the scene at regular intervals.
	 */
	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	/**
	 * Initializes the background image and sets up keyboard controls for player movement and firing.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP) user.moveUp();
				if (kc == KeyCode.DOWN) user.moveDown();
				if (kc == KeyCode.SPACE) fireProjectile();
			}
		});
		background.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode kc = e.getCode();
				if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
			}
		});
		root.getChildren().add(background);
	}

	/**
	 * Fires a projectile from the player plane and adds it to the scene and the projectile list.
	 */
	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
	}

	/**
	 * Generates enemy fire (projectiles) from all active enemies in the game.
	 */
	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	/**
	 * Spawns an enemy projectile and adds it to the scene and the enemy projectiles list.
	 *
	 * @param projectile The projectile to spawn.
	 */
	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
		}
	}

	/**
	 * Updates the position and state of all actors (planes, projectiles) in the game.
	 */
	private void updateActors() {
		friendlyUnits.forEach(plane -> plane.updateActor());
		enemyUnits.forEach(enemy -> enemy.updateActor());
		userProjectiles.forEach(projectile -> projectile.updateActor());
		enemyProjectiles.forEach(projectile -> projectile.updateActor());
	}

	/**
	 * Removes all destroyed actors (planes, projectiles) from the game scene and the lists.
	 */
	private void removeAllDestroyedActors() {
		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	/**
	 * Removes destroyed actors from the given list and the scene.
	 *
	 * @param actors The list of actors to check for destruction.
	 */
	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
				.collect(Collectors.toList());
		root.getChildren().removeAll(destroyedActors);
		actors.removeAll(destroyedActors);
	}

	/**
	 * Handles collision detection between planes (user and enemy).
	 */
	private void handlePlaneCollisions() {
		handleCollisions(friendlyUnits, enemyUnits);
	}

	/**
	 * Handles collisions between user projectiles and enemy units.
	 */
	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	/**
	 * Handles collisions between enemy projectiles and friendly units (player).
	 */
	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	/**
	 * General method to handle collisions between two lists of actors, applying damage when necessary.
	 *
	 * @param actors1 The first list of actors to check for collisions.
	 * @param actors2 The second list of actors to check for collisions.
	 */
	private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor : actors2) {
			for (ActiveActorDestructible otherActor : actors1) {
				if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
					actor.takeDamage();
					otherActor.takeDamage();
				}
			}
		}
	}

	/**
	 * Handles enemy units penetrating past the defenses (screen width) and causing damage to the player.
	 */
	private void handleEnemyPenetration() {
		for (ActiveActorDestructible enemy : enemyUnits) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.destroy();
			}
		}
	}

	/**
	 * Updates the level view to reflect the player's remaining health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Updates the player's kill count based on the number of enemies destroyed.
	 */
	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	/**
	 * Checks if the enemy has passed the screen width and penetrated the player's defenses.
	 *
	 * @param enemy The enemy to check.
	 * @return True if the enemy has penetrated the defenses, false otherwise.
	 */
	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	/**
	 * Stops the game and shows the win image when the player wins the level.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	/**
	 * Stops the game and shows the game over image when the player loses the level.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	/**
	 * Returns the user (player) plane object.
	 *
	 * @return The user (player) plane.
	 */
	protected UserPlane getUser() {
		return user;
	}

	/**
	 * Returns the root group of the scene, where all game objects are added.
	 *
	 * @return The root group of the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Returns the current number of active enemy units in the game.
	 *
	 * @return The current number of active enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	/**
	 * Adds an enemy unit to the game and the scene.
	 *
	 * @param enemy The enemy unit to add.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
	}

	/**
	 * Returns the maximum Y position where enemies can spawn.
	 *
	 * @return The maximum Y position for enemy spawning.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the width of the game screen.
	 *
	 * @return The width of the screen.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user (player) has been destroyed.
	 *
	 * @return True if the user is destroyed, false otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Updates the number of active enemies in the game.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}
