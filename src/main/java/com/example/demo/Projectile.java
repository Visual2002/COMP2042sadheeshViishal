package com.example.demo;

public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructs a new Projectile instance with the specified parameters for its image and position.
	 * A projectile is a type of actor that is typically fired by a plane, and it can move across the screen.
	 *
	 * @param imageName The name of the image file representing the projectile.
	 * @param imageHeight The height of the projectile image.
	 * @param initialXPos The initial x-position of the projectile on the screen.
	 * @param initialYPos The initial y-position of the projectile on the screen.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * This method is called when the projectile takes damage. Since projectiles are typically destroyed
	 * upon impact, this method marks the projectile as destroyed.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Abstract method to update the position of the projectile.
	 * The exact implementation will depend on the specific type of projectile.
	 */
	@Override
	public abstract void updatePosition();
}
