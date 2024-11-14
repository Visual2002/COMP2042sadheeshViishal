package com.example.demo;

import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructs the LevelViewLevelTwo object, which is a specialized version of LevelView
	 * for level two, including the additional shield image unique to this level.
	 *
	 * @param root The root Group that contains all UI elements of the level.
	 * @param heartsToDisplay The number of hearts to initially display based on player's health.
	 */
	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
		addImagesToRoot();
	}

	/**
	 * Adds the shield image to the root group. This shield represents a defensive power-up
	 * available in LevelTwo.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Displays the shield image in the level. This shield provides defense for the player.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image in the level. This method is used to remove the shield from view
	 * when the shield is no longer available or active.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

}
