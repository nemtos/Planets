package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlanetMars extends SpaceObject{
	
	private final PhysicsProvider physics;
	
	public PlanetMars(Sun sun) {
		super("mars.png", .8f);
		this.physics = new RotationPhysicsProvider(sun, 26, .0053f, -5f);
	}
	
	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
}
