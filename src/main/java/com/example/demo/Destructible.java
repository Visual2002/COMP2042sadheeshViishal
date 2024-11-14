package com.example.demo;

/**
 * The Destructible interface represents an entity that can take damage and be destroyed.
 * Any class that implements this interface is expected to handle the logic for receiving damage
 * and being destroyed, typically when health reaches zero or another condition is met.
 */
public interface Destructible {

	/**
	 * Method to apply damage to the object. The specific logic for how damage is applied will
	 * be defined in the implementing class.
	 */
	void takeDamage();

	/**
	 * Method to destroy the object. This will typically set an internal state to indicate that
	 * the object is destroyed (e.g., marking it as inactive or removing it from the game).
	 */
	void destroy();
}
