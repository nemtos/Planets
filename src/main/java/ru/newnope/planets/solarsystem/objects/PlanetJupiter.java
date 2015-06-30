package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlanetJupiter extends SpaceObject{
	
	private final PhysicsProvider physics;
	
	public PlanetJupiter(Sun sun) {
		super("jupiter.png", 1.8f);
		this.physics = new RotationPhysicsProvider(sun, 35, .005f, -5f);
	}
	
	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
}
