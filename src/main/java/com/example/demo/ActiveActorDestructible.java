package com.example.demo;

/**
 * The ActiveActorDestructible class represents an actor in the game that can
 * take damage and be destroyed. This abstract class extends ActiveActor and
 * implements the Destructible interface, providing additional functionality
 * for destructible objects.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	/**
	 * Constructs a destructible ActiveActor with the specified image, position, and size.
	 *
	 * @param imageName The filename of the image to display for this actor.
	 * @param imageHeight The height to display the image for this actor.
	 * @param initialXPos The initial x-coordinate position of this actor.
	 * @param initialYPos The initial y-coordinate position of this actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;
	}

	/**
	 * Updates the position of this actor. Subclasses must define the behavior
	 * for updating the actor's position.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the actor's state. This method is abstract and must be implemented
	 * by subclasses to define specific behavior for each update cycle.
	 */
	public abstract void updateActor();

	/**
	 * Applies damage to this actor. The specific implementation of taking damage
	 * must be provided by subclasses.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys this actor, setting its destroyed status to true.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destruction status of this actor.
	 *
	 * @param isDestroyed True if the actor should be marked as destroyed, otherwise false.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Returns the destruction status of this actor.
	 *
	 * @return True if the actor is destroyed, otherwise false.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
