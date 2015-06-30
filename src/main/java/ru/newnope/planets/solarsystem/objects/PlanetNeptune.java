package ru.newnope.planets.solarsystem.objects;

import ru.newnope.planets.physics.PhysicsProvider;
import ru.newnope.planets.physics.RotationPhysicsProvider;
import ru.newnope.planets.solarsystem.SpaceObject;

public class PlanetNeptune extends SpaceObject{
	
	private final PhysicsProvider physics;
	
	public PlanetNeptune(Sun sun) {
		super("neptune.png", 1.5f);
		this.physics = new RotationPhysicsProvider(sun, 65, .003f, -5f);
	}
	
	@Override
	public PhysicsProvider getPhysics() {
		return physics;
	}
	
}
