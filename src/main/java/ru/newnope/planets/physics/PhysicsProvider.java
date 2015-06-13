package ru.newnope.planets.physics;

import ru.newnope.planets.render.Coord3D;

public abstract class PhysicsProvider {
	
	/**
	 * @return new coordinates or null if no update needed
	 */
	public abstract Coord3D updatePosition();
	
}
